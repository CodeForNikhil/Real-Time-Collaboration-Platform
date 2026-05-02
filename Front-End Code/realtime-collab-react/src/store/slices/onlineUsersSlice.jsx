import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import axiosClient from '../../api/axiosClient';

export const fetchOnlineUsers = createAsyncThunk('onlineUsers/fetchOnlineUsers', async (_, thunkAPI) => {
  try {
    const response = await axiosClient.get('/presence/online-users');
    return response.data;
  } catch (error) {
    return thunkAPI.rejectWithValue('Unable to load online users');
  }
});

const onlineUsersSlice = createSlice({
  name: 'onlineUsers',
  initialState: {
    users: [],
    loading: false,
  },
  reducers: {
    userCameOnline(state, action) {
      const exists = state.users.some((user) => user.id === action.payload.id);
      if (!exists) {
        state.users.push(action.payload);
      }
    },
    userWentOffline(state, action) {
      state.users = state.users.filter((user) => user.id !== action.payload.userId);
    },
    resetOnlineUsers(state) {
      state.users = [];
      state.loading = false;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchOnlineUsers.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchOnlineUsers.fulfilled, (state, action) => {
        state.loading = false;
        state.users = action.payload;
      })
      .addCase(fetchOnlineUsers.rejected, (state) => {
        state.loading = false;
      });
  },
});

export const { userCameOnline, userWentOffline, resetOnlineUsers } = onlineUsersSlice.actions;
export default onlineUsersSlice.reducer;