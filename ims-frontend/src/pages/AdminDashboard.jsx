import { useAuth } from "../context/AuthContext";

function AdminDashboard() {

    const { user, logout } = useAuth();

    return (
        <div className="admin-dashboard">

            {/* Sidebar */}
            <aside className="sidebar">

                <div className="sidebar-logo">
                    MIS
                </div>

                <nav>

                    <a href="#dashboard">
                        Dashboard
                    </a>

                    <a href="#users">
                        Users
                    </a>

                    <a href="#invoices">
                        Invoices
                    </a>

                    <a href="#reports">
                        Reports
                    </a>

                </nav>

                <button
                    className="logout-button"
                    onClick={logout}
                >
                    Logout
                </button>

            </aside>


            {/* Main Content */}
            <main className="main-content">

                {/* Header */}
                <header className="dashboard-header">

                    <div>
                        <h1>Admin Dashboard</h1>
                        <p>
                            Welcome back, {user?.fullName || "Admin"}
                        </p>
                    </div>

                    <div className="profile">

                        <div className="profile-avatar">
                            {(user?.fullName || "A")
                                .charAt(0)
                                .toUpperCase()}
                        </div>

                        <div>
                            <strong>
                                {user?.fullName || "Admin"}
                            </strong>

                            <small>
                                {user?.email}
                            </small>
                        </div>

                    </div>

                </header>


                {/* Statistics */}
                <section className="stats-grid">

                    <div className="stat-card">
                        <h3>Total Users</h3>
                        <p>0</p>
                    </div>

                    <div className="stat-card">
                        <h3>Total Invoices</h3>
                        <p>0</p>
                    </div>

                    <div className="stat-card">
                        <h3>Pending Invoices</h3>
                        <p>0</p>
                    </div>

                    <div className="stat-card">
                        <h3>Total Revenue</h3>
                        <p>₹0</p>
                    </div>

                </section>


                {/* Quick Actions */}
                <section className="dashboard-section">

                    <h2>Quick Actions</h2>

                    <div className="action-grid">

                        <button>
                            Manage Users
                        </button>

                        <button>
                            View Invoices
                        </button>

                        <button>
                            Create Invoice
                        </button>

                        <button>
                            View Reports
                        </button>

                    </div>

                </section>


                {/* Recent Activity */}
                <section className="dashboard-section">

                    <h2>Recent Activity</h2>

                    <div className="empty-state">

                        <p>
                            No recent activity available.
                        </p>

                    </div>

                </section>

            </main>

        </div>
    );
}

export default AdminDashboard;