<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="stylesheet" href="../../css/sidebar_header.css">
    <link rel="stylesheet" href="../../css/profile.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <!-- Sidebar -->
        <!-- <th:block th:include="sidebar_profile :: sidebar"></th:block> Include sidebar using Thymeleaf -->
		<div th:replace="~{school_sidebar :: school_sidebar('profile')}"></div>
        
        <!-- Main Content -->
        <main class="content">
            <!-- header -->
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
            
            <h1>Profile</h1>
            <div class="form-container">
                <!-- Profile Image -->
                <div class="image-upload">
                    <div class="image-circle">
                        <img th:src="@{../../img/profile.png}" alt="Profile Image">
                    </div>
                </div>

                <!-- Display Profile Information -->
                <div class="form-grid">
                    <!-- Row 1 -->
                    <div class="form-group">
                        <label for="fullName">Admin Name (as per IC)</label>
                        <input type="text" id="fullName" name="fullName" th:value="${user.fullName}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="icNumber">IC Number</label>
                        <input type="text" id="icNumber" name="icNumber" th:value="${user.icNumber}" readonly>
                    </div>

                    <!-- Row 2 -->
                    <div class="form-group">
                        <label for="schoolName">School Name</label>
                        <input type="text" id="schoolName" name="schoolName" th:value="${user.schoolName}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" th:value="${user.email}" readonly>
                    </div>

                    <!-- Row 3 -->
                    <div class="form-group">
                        <label for="contactNumber">Contact Number</label>
                        <input type="text" id="contactNumber" name="contactNumber" th:value="${user.contactNumber}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="district">District</label>
                        <input type="text" id="district" name="district" th:value="${user.district}" readonly>
                    </div>

                    <!-- Row 4 -->
                    <div class="form-group">
                        <label for="ytbLink">YouTube Channel Link</label>
                        <a th:href="${user.youtubeLink}" target="_blank" id="ytbLink" th:text="${user.youtubeLink}"></a>
                    </div>
                    <div class="form-group">
                        <label for="ytbName">YouTube Channel Name</label>
                        <input type="text" id="ytbName" name="ytbName" th:value="${user.youtubeChannelName}" readonly>
                    </div>
                </div>

                <!-- Button to edit profile -->
                <div class="form-buttons">
                    <button type="button" class="btn btn-primary" onclick="window.location.href='/TVPSS_Project/school/profile/editProfile'">Edit</button>
                </div>
            </div>
        </main>
    </div>
</body>
</html>
