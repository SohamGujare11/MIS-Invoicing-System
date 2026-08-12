import { useEffect, useState } from "react";
import { Link, useSearchParams } from "react-router-dom";

function VerifyEmail() {

    const [searchParams] = useSearchParams();

    const token = searchParams.get("token");

    const [status, setStatus] = useState("loading");
    const [message, setMessage] = useState("");

    useEffect(() => {

        if (!token) {
            setStatus("error");
            setMessage("Invalid or missing verification token.");
            return;
        }

        const verifyEmail = async () => {

            try {

                const response = await fetch(
                    `http://localhost:8080/api/auth/verify-email?token=${encodeURIComponent(token)}`
                );

                const data = await response.json();

                if (!response.ok) {
                    throw new Error(
                        data.message ||
                        data.error ||
                        "Email verification failed."
                    );
                }

                setStatus("success");

                setMessage(
                    data.message ||
                    "Email verified successfully. You can now login."
                );

            } catch (error) {

                setStatus("error");

                setMessage(
                    error.message ||
                    "Email verification failed."
                );
            }
        };

        verifyEmail();

    }, [token]);

    return (
        <div className="auth-container">

            <div className="auth-card">

                <h1>MIS Invoicing System</h1>

                {status === "loading" && (
                    <>
                        <h2>Verifying Email...</h2>

                        <p>
                            Please wait while we verify your email address.
                        </p>
                    </>
                )}

                {status === "success" && (
                    <>
                        <h2>Email Verified Successfully</h2>

                        <div className="success-message">
                            {message}
                        </div>

                        <div className="auth-links">
                            <Link to="/login">
                                Go to Login
                            </Link>
                        </div>
                    </>
                )}

                {status === "error" && (
                    <>
                        <h2>Email Verification Failed</h2>

                        <div className="error-message">
                            {message}
                        </div>

                        <div className="auth-links">

                            <Link to="/login">
                                Back to Login
                            </Link>

                        </div>
                    </>
                )}

            </div>

        </div>
    );
}

export default VerifyEmail;