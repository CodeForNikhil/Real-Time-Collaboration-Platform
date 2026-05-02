import React, { useMemo, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { sendChatMessage, sendTypingEvent } from "../store/slices/socketSlice";

export default function ChatWindow() {
  const dispatch = useDispatch();

  const currentUser = useSelector((state) => state.auth.currentUser);
  const selectedUser = useSelector((state) => state.chat.selectedUser);
  const conversations = useSelector((state) => state.chat.conversations);
  const typingUsers = useSelector((state) => state.chat.typingUsers);

  const [text, setText] = useState("");

  const messages = useMemo(() => {
    if (!selectedUser) return [];
    return conversations[selectedUser.id] || [];
  }, [conversations, selectedUser]);

  const handleSend = () => {
    if (!selectedUser || !currentUser || !text.trim()) return;

    const payload = {
      senderId: currentUser.id,
      receiverId: selectedUser.id,
      content: text.trim(),
      messageType: "TEXT",
    };

    dispatch(sendChatMessage(payload));
    setText("");
  };

  const handleTyping = (value) => {
    setText(value);

    if (selectedUser && currentUser) {
      dispatch(
        sendTypingEvent({
          senderId: currentUser.id,
          receiverId: selectedUser.id,
          typing: value.length > 0,
        })
      );
    }
  };

  if (!selectedUser) {
    return (
      <section className="chat-panel empty-panel">
        <h2>Select a logged-in user</h2>
        <p>Once selected, you can chat, start a voice call, or start a video call.</p>
      </section>
    );
  }

  return (
    <section className="chat-panel">
      <div className="chat-header">
        <div>
          <h2>{selectedUser.fullName}</h2>
          <p>{selectedUser.role}</p>
        </div>

        <span className="presence-pill">Online</span>
      </div>

      <div className="message-list">
        {messages.map((message, index) => (
          <div
            className={`message-bubble ${
              message.senderId === currentUser?.id ? "own" : ""
            }`}
            key={index}
          >
            <p>{message.content}</p>
          </div>
        ))}
      </div>

      {typingUsers[selectedUser.id] && (
        <div className="typing-state">Typing...</div>
      )}

      <div className="composer">
        <input
          placeholder={`Message ${selectedUser.fullName}`}
          value={text}
          onChange={(event) => handleTyping(event.target.value)}
          onKeyDown={(event) => {
            if (event.key === "Enter") {
              handleSend();
            }
          }}
        />

        <button onClick={handleSend}>Send</button>
      </div>
    </section>
  );
}