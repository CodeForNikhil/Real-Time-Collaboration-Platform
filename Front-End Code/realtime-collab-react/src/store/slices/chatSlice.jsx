import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axiosClient from "../../api/axiosClient";

export const fetchChatHistory = createAsyncThunk(
  "chat/fetchChatHistory",
  async (userId, thunkAPI) => {
    try {
      const response = await axiosClient.get(`/chat/history/${userId}`);
      return {
        userId,
        messages: response.data,
      };
    } catch (error) {
      return thunkAPI.rejectWithValue("Unable to load chat history");
    }
  }
);

const initialState = {
  selectedUser: null,
  conversations: {},
  typingUsers: {},
};

const chatSlice = createSlice({
  name: "chat",
  initialState,

  reducers: {
    selectUser(state, action) {
      state.selectedUser = action.payload;
    },

    appendIncomingMessage(state, action) {
      const message = action.payload;

      const otherUserId =
        state.selectedUser?.id === message.senderId
          ? message.senderId
          : message.receiverId;

      if (!state.conversations[otherUserId]) {
        state.conversations[otherUserId] = [];
      }

      state.conversations[otherUserId].push(message);
    },

    setTypingState(state, action) {
      state.typingUsers[action.payload.senderId] = action.payload.typing;
    },

    resetChatState() {
      return initialState;
    },
  },

  extraReducers: (builder) => {
    builder.addCase(fetchChatHistory.fulfilled, (state, action) => {
      state.conversations[action.payload.userId] = action.payload.messages;
    });
  },
});

export const {
  selectUser,
  appendIncomingMessage,
  setTypingState,
  resetChatState,
} = chatSlice.actions;

export default chatSlice.reducer;