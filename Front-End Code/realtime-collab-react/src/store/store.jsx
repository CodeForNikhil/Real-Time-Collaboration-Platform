import { configureStore } from '@reduxjs/toolkit';
import authReducer from './slices/authSlice';
import onlineUsersReducer from './slices/onlineUsersSlice';
import chatReducer from './slices/chatSlice';
import socketReducer from './slices/socketSlice';
import callReducer from './slices/callSlice';
 
export const store = configureStore({
  reducer: {
    auth: authReducer,
    onlineUsers: onlineUsersReducer,
    chat: chatReducer,
    socket: socketReducer,
    call: callReducer,
  },
});
