import React from "react";
import { useDispatch, useSelector } from "react-redux";

import { fetchChatHistory, selectUser, resetChatState } from "../store/slices/chatSlice";
import { logoutUser } from "../store/slices/authSlice";
import { disconnectWebSocket } from "../store/slices/socketSlice";
import { resetCallState } from "../store/slices/callSlice";
import { resetOnlineUsers } from "../store/slices/onlineUsersSlice";

export default function SidebarUsers() {
  const dispatch = useDispatch();

  const currentUser = useSelector((state) => state.auth.currentUser);
  const users = useSelector((state) => state.onlineUsers.users || []);
  const selectedUser = useSelector((state) => state.chat.selectedUser);

  const handleSelect = (user) => {
    dispatch(selectUser(user));
    dispatch(fetchChatHistory(user.id));
  };

  const handleLogout = () => {
    dispatch(disconnectWebSocket());
    dispatch(logoutUser());
    dispatch(resetChatState());
    dispatch(resetCallState());
    dispatch(resetOnlineUsers());

    window.location.href = "/login";
  };

  return (
    <aside className="sidebar">
      <div className="sidebar-header">
        <div>
          <h2>Online Users</h2>
          <p>Logged in as: {currentUser?.fullName || "User"}</p>
        </div>

        <button className="logout-button" onClick={handleLogout}>
          Logout
        </button>
      </div>

      <div className="user-list">
        {users.length === 0 ? (
          <p>No other users online</p>
        ) : (
          users.map((user) => (
            <button
              className={`user-card ${selectedUser?.id === user.id ? "active" : ""}`}
              key={user.id}
              onClick={() => handleSelect(user)}
            >
              <div>
                <strong>{user.fullName}</strong>
                <span>{user.role}</span>
              </div>

              <em>Online</em>
            </button>
          ))
        )}
      </div>
    </aside>
  );
}