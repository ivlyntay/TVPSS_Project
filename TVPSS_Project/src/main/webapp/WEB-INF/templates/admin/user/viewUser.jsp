<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View User Profile</title>
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
    <!-- <link rel="stylesheet" href="../../css/user.css?v=2"> -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container">
         <!-- Include Sidebar -->
        <div th:replace="~{admin_sidebar :: admin_sidebar('userList')}"></div>
        <main class="content">
            <!-- Include Header -->
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

            <!-- View User Profile Section -->
            <h1>User List&nbsp;&nbsp;>&nbsp;&nbsp;<span th:text="${user.fullName}"></span></h1>

            <section class="view-user-profile">
                <div class="form-container">
                    <div class="profile-details">
                        <div class="image-upload">
                            <img th:src="@{/img/default.jpg}" alt="User Profile" class="user-image">
                        </div>
                        <table>
                            <tr>
                                <td>Admin Name:</td>
                                <td th:text="${user.fullName}"></td>
                            </tr>
                            <tr>
                                <td>IC Number:</td>
                                <td th:text="${user.icNumber}"></td>
                            </tr>
                            <tr>
                                <td>School Name:</td>
                                <td th:text="${user.schoolName}"></td>
                            </tr>
                            <tr>
                                <td>District:</td>
                                <td th:text="${user.district}"></td>
                            </tr>
                            <tr>
                                <td>Email:</td>
                                <td th:text="${user.email}"></td>
                            </tr>
                            <tr>
                                <td>Contact Number:</td>
                                <td th:text="${user.contactNumber}"></td>
                            </tr>
                            <tr>
                                <td>Youtube Channel:</td>
                                <td>
                                    <a th:href="${user.youtubeLink}" target="_blank" th:text="${user.youtubeLink}"></a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
               <div class="form-buttons">
                        <button type="button" class="btn btn-primary" th:onclick="|window.location.href='@{/admin/user/userList}'|">Back</button>
                </div>
            </section>
        </main>
        <script src="script.js"></script>
    </div>
</body>
</html>
