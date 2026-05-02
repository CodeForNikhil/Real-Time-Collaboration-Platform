import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { startCall, endCall, updateIncomingSignal } from "../store/slices/callSlice";
import { sendSignal } from "../store/slices/socketSlice";

export default function InteractionPanel() {
  const dispatch = useDispatch();

  const currentUser = useSelector((state) => state.auth.currentUser);
  const selectedUser = useSelector((state) => state.chat.selectedUser);
  const callState = useSelector((state) => state.call);

  const initiateCall = (mode) => {
    if (!currentUser || !selectedUser) return;

    const payload = {
      callerUserId: currentUser.id,
      targetUserId: selectedUser.id,
      type: mode === "VIDEO" ? "VIDEO_CALL_INIT" : "VOICE_CALL_INIT",
      payload: "",
    };

    dispatch(startCall({ user: selectedUser, mode }));
    dispatch(sendSignal(payload));
  };

  const acceptCall = () => {
    if (!currentUser || !callState.lastSignal) return;

    const incoming = callState.lastSignal;
    const mode = incoming.type === "VIDEO_CALL_INIT" ? "VIDEO" : "VOICE";

    dispatch(startCall({
      user: {
        id: incoming.callerUserId,
        fullName: `User ${incoming.callerUserId}`,
      },
      mode,
    }));

    dispatch(sendSignal({
      callerUserId: currentUser.id,
      targetUserId: incoming.callerUserId,
      type: mode === "VIDEO" ? "VIDEO_CALL_ACCEPTED" : "VOICE_CALL_ACCEPTED",
      payload: "",
    }));

    dispatch(updateIncomingSignal(null));
  };

  const rejectCall = () => {
    if (!currentUser || !callState.lastSignal) return;

    const incoming = callState.lastSignal;

    dispatch(sendSignal({
      callerUserId: currentUser.id,
      targetUserId: incoming.callerUserId,
      type: "CALL_REJECTED",
      payload: "",
    }));

    dispatch(updateIncomingSignal(null));
  };

  return (
    <aside className="interaction-panel">
      <h2>Interaction Options</h2>

      {!selectedUser ? (
        <p>Select a user first.</p>
      ) : (
        <>
          <div className="action-card">
            <h3>Direct Message</h3>
            <p>One-to-one chat with the selected online user.</p>
          </div>

          <button className="action-button" onClick={() => initiateCall("VOICE")}>
            Start Voice Call
          </button>

          <button className="action-button" onClick={() => initiateCall("VIDEO")}>
            Start Video Call
          </button>
        </>
      )}

      {callState.lastSignal &&
        (callState.lastSignal.type === "VOICE_CALL_INIT" ||
          callState.lastSignal.type === "VIDEO_CALL_INIT") && (
          <div className="call-panel">
            <h3>Incoming {callState.lastSignal.type === "VIDEO_CALL_INIT" ? "Video" : "Voice"} Call</h3>
            <p>From User ID: {callState.lastSignal.callerUserId}</p>

            <button className="action-button" onClick={acceptCall}>
              Accept
            </button>

            <button className="danger" onClick={rejectCall}>
              Reject
            </button>
          </div>
        )}

      {callState.activeCallUser && (
        <div className="call-panel">
          <h3>Active {callState.mode} call</h3>
          <p>{callState.activeCallUser.fullName}</p>

          <button className="danger" onClick={() => dispatch(endCall())}>
            End Call
          </button>
        </div>
      )}
    </aside>
  );
}