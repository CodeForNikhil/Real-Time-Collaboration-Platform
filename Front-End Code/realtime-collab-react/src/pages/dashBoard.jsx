import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import SidebarUsers from '../components/SidebarUsers';
import ChatWindow from '../components/ChatWindow';
import InteractionPanel from '../components/InteractionPanel';
import { fetchOnlineUsers } from '../store/slices/onlineUsersSlice';
 
export default function DashboardPage() {
  const dispatch = useDispatch();
  const connected = useSelector((state) => state.socket.connected);
 
  useEffect(() => {
    if (connected) {
      dispatch(fetchOnlineUsers());
    }
  }, [connected, dispatch]);
 
  return (
    <div className="dashboard-grid">
      <SidebarUsers />
      <ChatWindow />
      <InteractionPanel />
    </div>
  );
}
