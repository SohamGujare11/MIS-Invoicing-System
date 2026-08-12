import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { registerUser } from "../services/api";

function Register() {

    const navigate = useNavigate();

    const [fullName, setFullName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("SALES_PERSON");

    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (event) => {

        event.preventDefault();

        setError("");
        setSuccess("");
        setLoading(true);

        try {

            const response = await registerUser({
                fullName,
                email,
                password,
                role
            });

            setSuccess(
                response.message ||
                "Registration successful. Please verify your email."
            );

            // Clear form
            setFullName("");
            setEmail("");
            setPassword("");
            setRole("SALES_PERSON");

        } catch (error) {

            setError(
                error.message ||
                "Registration failed. Please try again."
            );

        } finally {

            setLoading(false);
        }
    };

    return (
        <div className="auth-container">

            <div className="auth-card">

                <h1>MIS Invoicing System</h1>

                <h2>Register</h2>

                {error && (
                    <div className="error-message">
                        {error}
                    </div>
                )}

                {success && (
                    <div className="success-message">
                        {success}
                    </div>
                )}

                <form onSubmit={handleSubmit}>

                    <div className="form-group">

                        <label>
                            Full Name
                        </label>

                        <input
                            type="text"
                            value={fullName}
                            onChange={(event) =>
                                setFullName(event.target.value)
                            }
                            placeholder="Enter your full name"
                            required
                        />

                    </div>

                    <div className="form-group">

                        <label>
                            Email
                        </label>

                        <input
                            type="email"
                            value={email}
                            onChange={(event) =>
                                setEmail(event.target.value)
                            }
                            placeholder="Enter your email"
                            required
                        />

                    </div>

                    <div className="form-group">

                        <label>
                            Password
                        </label>

                        <input
                            type="password"
                            value={password}
                            onChange={(event) =>
                                setPassword(event.target.value)
                            }
                            placeholder="Enter your password"
                            required
                            minLength="6"
                        />

                    </div>

                    <div className="form-group">

                        <label>
                            Role
                        </label>

                        <select
                            value={role}
                            onChange={(event) =>
                                setRole(event.target.value)
                            }
                        >
                            <option value="SALES_PERSON">
                                Sales Person
                            </option>

                            <option value="ADMIN">
                                Admin
                            </option>

                        </select>

                    </div>

                    <button
                        type="submit"
                        disabled={loading}
                    >
                        {loading
                            ? "Registering..."
                            : "Register"}
                    </button>

                </form>

                <div className="auth-links">

                    <p>
                        Already have an account?{" "}
                        <Link to="/login">
                            Login
                        </Link>
                    </p>

                </div>

            </div>

        </div>
    );
}

export default Register;