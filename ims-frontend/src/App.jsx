import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import { AuthProvider } from "./context/AuthContext";

import Login from "./pages/Login";
import Register from "./pages/Register";
import ForgotPassword from "./pages/ForgotPassword";
import ResetPassword from "./pages/ResetPassword";
import VerifyEmail from "./pages/VerifyEmail";

function App() {
    return (
        <AuthProvider>

            <BrowserRouter>

                <Routes>

                    {/* =========================
                        DEFAULT
                       ========================= */}

                    <Route
                        path="/"
                        element={
                            <Navigate
                                to="/login"
                                replace
                            />
                        }
                    />

                    {/* =========================
                        AUTHENTICATION
                       ========================= */}

                    <Route
                        path="/login"
                        element={<Login />}
                    />

                    <Route
                        path="/register"
                        element={<Register />}
                    />

                    <Route
                        path="/forgot-password"
                        element={<ForgotPassword />}
                    />

                    <Route
                        path="/reset-password"
                        element={<ResetPassword />}
                    />

                    <Route
                        path="/verify-email"
                        element={<VerifyEmail />}
                    />

                    {/* =========================
                        FALLBACK
                       ========================= */}

                    <Route
                        path="*"
                        element={
                            <Navigate
                                to="/login"
                                replace
                            />
                        }
                    />

                </Routes>

            </BrowserRouter>

        </AuthProvider>
    );
}

export default App;