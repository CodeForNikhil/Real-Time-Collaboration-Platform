import { createSlice } from '@reduxjs/toolkit';

const callSlice = createSlice({
  name: 'call',
  initialState: {
    activeCallUser: null,
    lastSignal: null,
    mode: null,
  },
  reducers: {
    startCall(state, action) {
      state.activeCallUser = action.payload.user;
      state.mode = action.payload.mode;
    },
    endCall(state) {
      state.activeCallUser = null;
      state.lastSignal = null;
      state.mode = null;
    },
    updateIncomingSignal(state, action) {
      state.lastSignal = action.payload;
    },
    resetCallState(state) {
      state.activeCallUser = null;
      state.lastSignal = null;
      state.mode = null;
    },
  },
});

export const { startCall, endCall, updateIncomingSignal, resetCallState } = callSlice.actions;
export default callSlice.reducer;