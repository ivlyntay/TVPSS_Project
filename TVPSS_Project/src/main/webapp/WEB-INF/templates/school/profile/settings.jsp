<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Settings</title>
    <link rel="stylesheet" href="../../css/sidebar_header.css">
    <link rel="stylesheet" href="../../css/settings.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        /* Password container for right-aligned eye icon */
        .password-container {
            position: relative;
            display: flex;
            align-items: center;
        }

        /* Style input field to make space for the icon */
        .password-container input {
            width: 100%;
            padding-right: 30px;  /* Add padding to make space for the icon */
        }

        /* Style the icon for the eye (positioned to the right of the input field) */
        .password-container i {
            position: absolute;
            right: 10px; /* Position the icon at the right side */
            cursor: pointer;
            font-size: 1.2em; /* Adjust icon size */
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Sidebar -->
        <div th:replace="~{school_sidebar :: school_sidebar('settings')}"></div>
        
        <!-- Main Content -->
        <main class="content">
            <!-- Header -->
            <header class="header">
                <div class="header-right">
                    <div class="profile">
                        <img th:src="@{/img/profile.png}" alt="Moni Roy" class="profile-image">
                        <div class="header-profile">
                            <span class="profile-name" th:text="${loggedInUser.fullName}"></span><br>

                            <span class="role" th:text="${loggedInUser.role}"></span>
                        </div>
                    </div>
                </div>
            </header>

            <h1>Settings</h1>
            <div class="form-container">
                <!-- Change Password Form -->
                <h3>Change Password</h3>
                <form th:action="@{/school/profile/updatePassword}" method="post">
                    <div class="form-group">
                        <label for="currentPassword">Current Password</label>
                        <div class="password-container">
                            <input type="password" id="currentPassword" name="currentPassword" th:value="${user.password}" readonly required>
                            <i class="bi bi-eye" id="toggleCurrentPassword" onclick="togglePasswordVisibility('currentPassword')"></i>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="newPassword">New Password</label>
                        <div class="password-container">
                            <input type="password" id="newPassword" name="newPassword" required>
                            <i class="bi bi-eye" id="toggleNewPassword" onclick="togglePasswordVisibility('newPassword')"></i>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="confirmPassword">Confirm Password</label>
                        <div class="password-container">
                            <input type="password" id="confirmPassword" name="confirmPassword" required>
                            <i class="bi bi-eye" id="toggleConfirmPassword" onclick="togglePasswordVisibility('confirmPassword')"></i>
                        </div>
                    </div>

                    <div class="form-buttons">
                        <button type="submit" class="btn btn-primary">Change Password</button>
                    </div>
                </form>
            </div>
        </main>

        <script>
            // Function to toggle password visibility
            function togglePasswordVisibility(passwordFieldId) {
                var passwordField = document.getElementById(passwordFieldId);
                var icon = document.getElementById("toggle" + passwordFieldId.charAt(0).toUpperCase() + passwordFieldId.slice(1));

                if (passwordField.type === "password") {
                    passwordField.type = "text";  // Show password
                    icon.classList.remove("bi-eye");  // Change icon to eye-slash
                    icon.classList.add("bi-eye-slash");
                } else {
                    passwordField.type = "password";  // Hide password
                    icon.classList.remove("bi-eye-slash");  // Change icon to eye
                    icon.classList.add("bi-eye");
                }
            }
        </script>
    </div>
</body>
</html>
