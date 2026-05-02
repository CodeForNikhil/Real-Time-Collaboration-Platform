import React, { useEffect } from "react";
import { Navigate, Route, Routes } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";

import LoginPage from "./pages/loginPage";
import DashboardPage from "./pages/dashboard";

import { fetchMe } from "./store/slices/authSlice";
import { connectWebSocket, disconnectWebSocket } from "./store/slices/socketSlice";

function PrivateRoute({ children }) {
  const token = useSelector((state) => state.auth.token);
  return token ? children : <Navigate to="/login" replace />;
}

export default function App() {
  const dispatch = useDispatch();
  const token = useSelector((state) => state.auth.token);

  useEffect(() => {
    if (token) {
      dispatch(fetchMe())
        .unwrap()
        .then(() => {
          dispatch(connectWebSocket());
        })
        .catch(() => {
          dispatch(disconnectWebSocket());
        });
    } else {
      dispatch(disconnectWebSocket());
    }

    return () => {
      dispatch(disconnectWebSocket());
    };
  }, [dispatch, token]);

  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />

      <Route
        path="/"
        element={
          <PrivateRoute>
            <DashboardPage />
          </PrivateRoute>
        }
      />

      <Route
        path="/dashboard"
        element={
          <PrivateRoute>
            <DashboardPage />
          </PrivateRoute>
        }
      />

      <Route path="*" element={<Navigate to={token ? "/dashboard" : "/login"} replace />} />
    </Routes>
  );
}