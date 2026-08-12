import { createContext, useContext, useEffect, useState } from "react";
import { loginUser } from "../services/api";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {

    const [user, setUser] = useState(null);
    const [token, setToken] = useState(null);

    useEffect(() => {

        const savedToken = localStorage.getItem("token");
        const savedUser = localStorage.getItem("user");

        if (savedToken && savedUser) {
            setToken(savedToken);
            setUser(JSON.parse(savedUser));
        }

    }, []);

    const login = async (email, password) => {

        const response = await loginUser(email, password);

        localStorage.setItem("token", response.token);

        const userData = {
            userId: response.userId,
            fullName: response.fullName,
            email: response.email,
            role: response.role
        };

        localStorage.setItem(
            "user",
            JSON.stringify(userData)
        );

        setToken(response.token);
        setUser(userData);

        return response;
    };

    const logout = () => {

        localStorage.removeItem("token");
        localStorage.removeItem("user");

        setToken(null);
        setUser(null);
    };

    return (
        <AuthContext.Provider
            value={{
                user,
                token,
                login,
                logout,
                isAuthenticated: !!token
            }}
        >
            {children}
        </AuthContext.Provider>
    );
}

export function useAuth() {
    return useContext(AuthContext);
}