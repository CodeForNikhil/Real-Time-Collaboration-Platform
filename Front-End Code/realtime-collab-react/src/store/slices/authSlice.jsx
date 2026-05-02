import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axiosClient from "../../api/axiosClient";

const tokenFromStorage = localStorage.getItem("collab_token");

export const loginUser = createAsyncThunk("auth/loginUser", async (payload, thunkAPI) => {
  try {
    const response = await axiosClient.post("/auth/login", payload);
    return response.data;
  } catch (error) {
    return thunkAPI.rejectWithValue(error.response?.data?.message || "Login failed");
  }
});

export const registerUser = createAsyncThunk("auth/registerUser", async (payload, thunkAPI) => {
  try {
    const response = await axiosClient.post("/auth/register", payload);
    return response.data;
  } catch (error) {
    return thunkAPI.rejectWithValue(error.response?.data?.message || "Registration failed");
  }
});

export const fetchMe = createAsyncThunk("auth/fetchMe", async (_, thunkAPI) => {
  try {
    const response = await axiosClient.get("/users/me");
    return response.data;
  } catch (error) {
    return thunkAPI.rejectWithValue("Unable to fetch current user");
  }
});

export const logoutUser = createAsyncThunk("auth/logoutUser", async (_, thunkAPI) => {
  try {
    await axiosClient.post("/auth/logout");
    return true;
  } catch (error) {
    return thunkAPI.rejectWithValue(error.response?.data?.message || "Logout failed");
  }
});

const authSlice = createSlice({
  name: "auth",
  initialState: {
    token: tokenFromStorage,
    currentUser: null,
    loading: false,
    error: null,
    mode: "login",
  },
  reducers: {
    logout(state) {
      state.token = null;
      state.currentUser = null;
      state.error = null;
      state.mode = "login";
      localStorage.removeItem("collab_token");
    },
    setAuthMode(state, action) {
      state.mode = action.payload;
      state.error = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(loginUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(loginUser.fulfilled, (state, action) => {
        state.loading = false;
        state.token = action.payload.token;

        state.currentUser = {
          id: action.payload.userId,
          fullName: action.payload.fullName,
          role: action.payload.role,
        };

        localStorage.setItem("collab_token", action.payload.token);
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })

      .addCase(registerUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(registerUser.fulfilled, (state, action) => {
        state.loading = false;
        state.token = action.payload.token;

        state.currentUser = {
          id: action.payload.userId,
          fullName: action.payload.fullName,
          role: action.payload.role,
        };

        localStorage.setItem("collab_token", action.payload.token);
      })
      .addCase(registerUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })

      .addCase(fetchMe.fulfilled, (state, action) => {
        state.currentUser = action.payload;
      })
      .addCase(fetchMe.rejected, (state, action) => {
        state.currentUser = null;
        state.error = action.payload;
      })

      .addCase(logoutUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(logoutUser.fulfilled, (state) => {
        state.loading = false;
        state.token = null;
        state.currentUser = null;
        state.error = null;
        state.mode = "login";
        localStorage.removeItem("collab_token");
      })
      .addCase(logoutUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export const { logout, setAuthMode } = authSlice.actions;
export default authSlice.reducer;