import { useState } from "react";
import { Link, useSearchParams, useNavigate } from "react-router-dom";
import { resetPassword } from "../services/api";

function ResetPassword() {

    const [searchParams] = useSearchParams();
    const navigate = useNavigate();

    const token = searchParams.get("token");

    const [newPassword, setNewPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (event) => {

        event.preventDefault();

        setMessage("");
        setError("");

        // Check token
        if (!token) {
            setError("Invalid password reset link.");
            return;
        }

        // Check passwords
        if (newPassword !== confirmPassword) {
            setError("Passwords do not match.");
            return;
        }

        // Backend requires minimum 8 characters
        if (newPassword.length < 8) {
            setError("Password must contain at least 8 characters.");
            return;
        }

        setLoading(true);

        try {

            await resetPassword({
                token: token,
                newPassword: newPassword
            });

            setMessage(
                "Password reset successfully. You can now login."
            );

            setNewPassword("");
            setConfirmPassword("");

        } catch (error) {

            setError(
                error.message ||
                "Unable to reset password."
            );

        } finally {

            setLoading(false);
        }
    };

    return (
        <div className="auth-container">

            <div className="auth-card">

                <h1>MIS Invoicing System</h1>

                <h2>Reset Password</h2>

                <p>
                    Enter your new password below.
                </p>

                {error && (
                    <div className="error-message">
                        {error}
                    </div>
                )}

                {message && (
                    <div className="success-message">
                        {message}
                    </div>
                )}

                <form onSubmit={handleSubmit}>

                    <div className="form-group">

                        <label>
                            New Password
                        </label>

                        <input
                            type="password"
                            value={newPassword}
                            onChange={(event) =>
                                setNewPassword(event.target.value)
                            }
                            placeholder="Enter new password"
                            minLength="8"
                            required
                        />

                    </div>

                    <div className="form-group">

                        <label>
                            Confirm Password
                        </label>

                        <input
                            type="password"
                            value={confirmPassword}
                            onChange={(event) =>
                                setConfirmPassword(event.target.value)
                            }
                            placeholder="Confirm new password"
                            minLength="8"
                            required
                        />

                    </div>

                    <button
                        type="submit"
                        disabled={loading}
                    >
                        {loading
                            ? "Resetting..."
                            : "Reset Password"}
                    </button>

                </form>

                <div className="auth-links">

                    <Link to="/login">
                        Back to Login
                    </Link>

                </div>

            </div>

        </div>
    );
}

export default ResetPassword;