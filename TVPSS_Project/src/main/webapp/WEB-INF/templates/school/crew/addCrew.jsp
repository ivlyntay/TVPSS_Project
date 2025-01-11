<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Crew</title>
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <!-- Include Sidebar -->
        <div th:replace="~{school_sidebar :: school_sidebar('crewList')}"></div>

        <!-- Main Content -->
        <main class="content">
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

            <h1>Add New Crew</h1>
            <div class="form-container">
                <!-- Form Fields -->
                <form th:action="@{/school/crew/add}" method="post" enctype="multipart/form-data">
                    <div class="form-group image-upload">
                        <div class="image-circle" id="image-circle">
                            <i class="bi bi-camera-fill" id="camera-icon" style="font-size: 30px;"></i>
                            <img id="profile-pic" alt="Default Camera Icon" style="display: none;">
                        </div>
                        <label for="crew-photo" class="upload-label">Upload Crew Photo</label>
                        <input type="file" id="crew-photo" name="crewPhoto" style="opacity: 0" accept="image/*">
                    </div>

                    <!-- Error Handling -->
                    <div th:if="${error != null}" class="alert alert-danger">
                        <span th:text="${error}"></span>
                    </div>

                    <div class="form-grid">
                        <!-- Row 1 -->
                        <div class="form-group">
                            <label for="fullName">Full Name (as per IC)</label>
                            <input type="text" id="fullName" name="fullName" placeholder="Enter full name" required>
                        </div>
                        <div class="form-group">
                            <label for="icNumber">IC Number</label>
                            <input type="text" id="icNumber" name="icNumber" placeholder="Enter IC Number" required>
                        </div>

                        <!-- Row 2 -->
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" placeholder="Enter email" required>
                        </div>
                        <div class="form-group">
                            <label for="contactNumber">Contact Number</label>
                            <input type="text" id="contactNumber" name="contactNumber" placeholder="Enter contact number" required>
                        </div>

                        <!-- Row 3 -->
                        <div class="form-group">
                            <label for="gender">Gender</label>
                            <select id="gender" name="gender" required>
                                <option value="">Select gender</option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="role">Role</label>
                            <select id="role" name="role" required>
                                <option value="">Select role</option>
                                <option value="Reporter">Reporter</option>
                                <option value="Assistant Director">Assistant Director</option>
                                <option value="Camera Operator">Camera Operator</option>
                                <option value="Script Writer">Script Writer</option>
                                <option value="Video Editor">Video Editor</option>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" name="userId" th:value="${session.userId}" />
                    
                    <!-- Form Buttons -->
                    <div class="form-buttons">
                        <button type="submit" class="btn btn-primary" name="action" value="add">Add Now</button>
                        <button type="button" class="btn btn-secondary" th:onclick="|window.location.href='@{/school/crew/crewList}'|">Cancel</button>
                    </div>
                </form>
            </div>
        </main>
    </div>
    <script> 
    let profilePic = document.getElementById("profile-pic");
    let inputFile = document.getElementById("crew-photo");
    let cameraIcon = document.getElementById("camera-icon");

    inputFile.onchange = function() {
        if (inputFile.files && inputFile.files[0]) {
            profilePic.src = URL.createObjectURL(inputFile.files[0]);
            profilePic.style.display = 'block';
            cameraIcon.style.display = 'none'; // Hide camera icon once image is uploaded
        }
    }
    </script>
</body>
</html>
