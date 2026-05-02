import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { loginUser, registerUser, setAuthMode } from '../store/slices/authSlice';
import { useNavigate } from 'react-router-dom';

export default function LoginPage() {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const loading = useSelector((state) => state.auth.loading);
  const error = useSelector((state) => state.auth.error);
  const mode = useSelector((state) => state.auth.mode);

  const [form, setForm] = useState({
    username: '',
    password: '',
    fullName: '',
    role: '',
  });

  const onSubmit = async (event) => {
    event.preventDefault();

    const payload =
      mode === 'register'
        ? {
            username: form.username,
            password: form.password,
            fullName: form.fullName,
            role: form.role,
          }
        : {
            username: form.username,
            password: form.password,
          };

    const result =
      mode === 'register'
        ? await dispatch(registerUser(payload))
        : await dispatch(loginUser(payload));

    if (registerUser.fulfilled.match(result) || loginUser.fulfilled.match(result)) {
      navigate('/');
    }
  };

  const switchMode = (nextMode) => {
    dispatch(setAuthMode(nextMode));
    setForm({
      username: '',
      password: '',
      fullName: '',
      role: '',
    });
  };

  return (
    <div className="login-shell">
      <form className="login-card" onSubmit={onSubmit}>
        <h1>{mode === 'login' ? 'CollabSpace Login' : 'CollabSpace Register'}</h1>
        <p>
          {mode === 'login'
            ? 'Login to view other active users and decide how to interact.'
            : 'Create your account to join the workspace and interact with other logged-in users.'}
        </p>

        {mode === 'register' && (
          <>
            <input
              placeholder="Full Name"
              value={form.fullName}
              onChange={(event) => setForm({ ...form, fullName: event.target.value })}
            />
            <input
              placeholder="Role"
              value={form.role}
              onChange={(event) => setForm({ ...form, role: event.target.value })}
            />
          </>
        )}

        <input
          placeholder="Username"
          value={form.username}
          onChange={(event) => setForm({ ...form, username: event.target.value })}
        />

        <input
          type="password"
          placeholder="Password"
          value={form.password}
          onChange={(event) => setForm({ ...form, password: event.target.value })}
        />

        <button disabled={loading}>
          {loading
            ? mode === 'login'
              ? 'Signing in...'
              : 'Creating account...'
            : mode === 'login'
            ? 'Login'
            : 'Register'}
        </button>

        {error && <span className="error">{error}</span>}

        <div className="auth-switch">
          {mode === 'login' ? (
            <p>
              First time user?{' '}
              <button type="button" className="link-button" onClick={() => switchMode('register')}>
                Register here
              </button>
            </p>
          ) : (
            <p>
              Already have an account?{' '}
              <button type="button" className="link-button" onClick={() => switchMode('login')}>
                Login here
              </button>
            </p>
          )}
        </div>
      </form>
    </div>
  );
}