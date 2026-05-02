import { createSlice } from "@reduxjs/toolkit";
import { Client } from "@stomp/stompjs";

import { fetchOnlineUsers, userCameOnline, userWentOffline } from "./onlineUsersSlice";
import { appendIncomingMessage, setTypingState } from "./chatSlice";
import { updateIncomingSignal } from "./callSlice";

let stompClient = null;

const socketSlice = createSlice({
  name: "socket",
  initialState: {
    connected: false,
  },
  reducers: {
    socketConnected(state) {
      state.connected = true;
    },
    socketDisconnected(state) {
      state.connected = false;
    },
  },
});

export const { socketConnected, socketDisconnected } = socketSlice.actions;

export const connectWebSocket = () => (dispatch, getState) => {
  if (stompClient && stompClient.active) return;

  const token = getState().auth.token;

  stompClient = new Client({
    brokerURL: "ws://localhost:8080/ws",
    reconnectDelay: 5000,

    connectHeaders: {
      Authorization: token ? `Bearer ${token}` : "",
    },

    debug: (str) => {
      console.log("STOMP:", str);
    },

    onConnect: () => {
      console.log("WebSocket connected");

      dispatch(socketConnected());
      dispatch(fetchOnlineUsers());

      const currentUser = getState().auth.currentUser;

      stompClient.subscribe("/topic/presence", (message) => {
        const payload = JSON.parse(message.body);
        const latestUser = getState().auth.currentUser;

        if (payload.userId === latestUser?.id) {
          return;
        }

        if (payload.status === "ONLINE") {
          dispatch(
            userCameOnline({
              id: payload.userId,
              fullName: payload.fullName || `User ${payload.userId}`,
              role: payload.role || "USER",
              online: true,
            })
          );
        } else {
          dispatch(userWentOffline(payload));
        }
      });

      if (currentUser?.id) {
        stompClient.subscribe(`/topic/messages/${currentUser.id}`, (message) => {
          dispatch(appendIncomingMessage(JSON.parse(message.body)));
        });

        stompClient.subscribe(`/topic/signaling/${currentUser.id}`, (message) => {
          dispatch(updateIncomingSignal(JSON.parse(message.body)));
        });

        stompClient.subscribe(`/topic/typing/${currentUser.id}`, (message) => {
          dispatch(setTypingState(JSON.parse(message.body)));
        });
      } else {
        console.warn("Current user not found. Message subscriptions skipped.");
      }
    },

    onStompError: (frame) => {
      console.error("STOMP broker error:", frame.headers["message"]);
      console.error("Details:", frame.body);
    },

    onWebSocketError: (error) => {
      console.error("WebSocket error:", error);
    },

    onDisconnect: () => {
      console.log("WebSocket disconnected");
      dispatch(socketDisconnected());
    },
  });

  stompClient.activate();
};

export const disconnectWebSocket = () => (dispatch) => {
  if (stompClient) {
    stompClient.deactivate();
    stompClient = null;
    dispatch(socketDisconnected());
  }
};

export const sendChatMessage = (payload) => () => {
  if (stompClient?.connected) {
    stompClient.publish({
      destination: "/app/chat.send",
      body: JSON.stringify(payload),
    });
  } else {
    console.warn("WebSocket is not connected");
  }
};

export const sendTypingEvent = (payload) => () => {
  if (stompClient?.connected) {
    stompClient.publish({
      destination: "/app/chat.typing",
      body: JSON.stringify(payload),
    });
  }
};

export const sendSignal = (payload) => () => {
  if (stompClient?.connected) {
    console.log("Sending signal:", payload);

    stompClient.publish({
      destination: "/app/signal.exchange",
      body: JSON.stringify(payload),
    });
  } else {
    console.warn("WebSocket is not connected");
  }
};

export default socketSlice.reducer;