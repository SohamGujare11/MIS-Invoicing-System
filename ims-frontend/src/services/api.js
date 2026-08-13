coconst API_BASE_URL = "https://mis-invoicing-system-production-559d.up.railway.app/api";

async function request(endpoint, options = {}) {

    const response = await fetch(
        `${API_BASE_URL}${endpoint}`,
        {
            ...options,
            headers: {
                "Content-Type": "application/json",
                ...(options.headers || {})
            }
        }
    );

    const text = await response.text();

    let data;

    try {
        data = text ? JSON.parse(text) : {};
    } catch {
        data = {
            message: text
        };
    }

    if (!response.ok) {

        throw new Error(
            data.message ||
            data.error ||
            `Request failed with status ${response.status}`
        );
    }

    return data;
}


// =========================
// REGISTER
// =========================

export function registerUser(data) {

    return request("/auth/register", {
        method: "POST",
        body: JSON.stringify(data)
    });
}


// =========================
// LOGIN
// =========================

export function loginUser(data) {

    return request("/auth/login", {
        method: "POST",
        body: JSON.stringify(data)
    });
}


// =========================
// FORGOT PASSWORD
// =========================

export function forgotPassword(email) {

    return request("/auth/forgot-password", {
        method: "POST",
        body: JSON.stringify({
            email: email
        })
    });
}


// =========================
// RESET PASSWORD
// =========================

export function resetPassword(data) {

    return request("/auth/reset-password", {
        method: "POST",
        body: JSON.stringify(data)
    });
}