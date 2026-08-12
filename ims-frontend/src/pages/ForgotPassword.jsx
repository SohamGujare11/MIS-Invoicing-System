import { useState } from "react";
import { Link } from "react-router-dom";
import { forgotPassword } from "../services/api";

function ForgotPassword() {

    const [email, setEmail] = useState("");

    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (event) => {

        event.preventDefault();

        setMessage("");
        setError("");
        setLoading(true);

        try {

            const response = await forgotPassword(email);

            setMessage(
                response.message ||
                "Password reset request created successfully. Please check your email."
            );

            setEmail("");

        } catch (error) {

            setError(
                error.message ||
                "Unable to process password reset request."
            );

        } finally {

            setLoading(false);
        }
    };

    return (
        <div className="auth-container">

            <div className="auth-card">

                <h1>MIS Invoicing System</h1>

                <h2>Forgot Password</h2>

                <p>
                    Enter your registered email address to
                    request a password reset.
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

                        <label>Email</label>

                        <input
                            type="email"
                            value={email}
                            onChange={(e) =>
                                setEmail(e.target.value)
                            }
                            placeholder="Enter your registered email"
                            required
                        />

                    </div>

                    <button
                        type="submit"
                        disabled={loading}
                    >
                        {loading
                            ? "Processing..."
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

export default ForgotPassword;