<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Settings</title>
    <link rel="stylesheet" href="../../css/sidebar_header.css">
    <link rel="stylesheet" href="../../css/settings.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <!-- Sidebar -->
        <div th:replace="~{admin_sidebar :: admin_sidebar('profile')}"></div>
        
        <!-- Main Content -->
        <main class="content">
            <!-- Header -->
            <header class="header">
                <div class="header-right">
                    <div class="profile">
                        <img th:src="@{/img/profile.png}" alt="Moni Roy" class="profile-image">
                        <div class="header-profile">
                            <span class="profile-name">Moni Roy</span><br>
                            <span class="role">Admin</span>
                        </div>
                    </div>
                </div>
            </header>

            <h1>Settings</h1>
            <div class="form-container">
                <!-- Change Password Form -->
                <h3>Change Password</h3>
                <form th:action="@{/admin/profile/updatePassword}" method="post">
                    <div class="form-group">
                        <label for="currentPassword">Current Password</label>
                        <input type="password" id="currentPassword" name="currentPassword" required>
                    </div>

                    <div class="form-group">
                        <label for="newPassword">New Password</label>
                        <input type="password" id="newPassword" name="newPassword" required>
                    </div>

                    <div class="form-group">
                        <label for="confirmPassword">Confirm Password</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" required>
                    </div>

                    <div class="form-buttons">
                        <button type="submit" class="btn btn-primary">Change Password</button>
                    </div>
                </form>
            </div>
        </main>

        <script src="../../js/setting.js"></script>
    </div>
</body>
</html>
