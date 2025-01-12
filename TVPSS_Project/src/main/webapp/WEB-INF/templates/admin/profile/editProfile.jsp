<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Profile</title>
<link rel="stylesheet" href="../../css/sidebar_header.css">
<link rel="stylesheet" href="../../css/profile.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<!-- Sidebar -->
		<div th:replace="~{admin_sidebar :: admin_sidebar('profile')}"></div>

		<!-- Main Content -->
		<main class="content">
			<!-- header -->
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

			<h1>Edit Profile</h1>
			<div class="form-container">
			<!-- Display Success Message -->
                <div th:if="${message}" class="alert alert-success">
                    <p th:text="${message}"></p>
                </div>
                
				<!-- Profile Image Upload -->
                <div class="image-upload">
                    <img th:src="@{/profile_photos/{photo}(photo=${user.photo != null ? user.photo : 'default.jpg'})}" 
                         alt="Profile Picture" 
                         id="profile-pic" 
                         class="profile-image">
                    <label for="profile-photo" class="upload-label">Update Profile Picture</label>
                    <input type="file" id="profile-photo" name="profilePhoto" style="opacity: 0" accept="image/*">
                </div>

				<!-- Form Fields (Editable) -->
				<form th:action="@{/admin/profile/updateProfile}" method="post" enctype="multipart/form-data" onsubmit="return confirmSubmit();">
					<div class="form-grid">
						<!-- Row 1 -->
						<div class="form-group">
							<label for="fullName">Admin Name</label>
							<input type="text" id="fullName" name="fullName" th:value="${user.fullName}" required>
						</div>
						<div class="form-group">
							<label for="icNumber">IC Number</label>
							<input type="text" id="icNumber" name="icNumber" th:value="${user.icNumber}" required>
						</div>
						
						<!-- Row 2 -->
						<div class="form-group">
							<label for="email">Email</label>
							<input type="email" id="email" name="email" th:value="${user.email}" required>
						</div>
						<div class="form-group">
							<label for="contactNumber">Contact Number</label>
							<input type="text" id="contactNumber" name="contactNumber" th:value="${user.contactNumber}" required>
						</div>
						
					</div>

					<!-- Submit Button -->
					<div class="form-buttons">
						<button type="submit" class="btn btn-primary">Save Changes</button>
						<button type="button" class="btn btn-secondary" th:onclick="|window.location.href='@{/admin/profile/profile}'|">Cancel</button>					
					</div>
				</form>
			</div>
		</main>
		<script>
		 let inputFile = document.getElementById("profile-photo");
	        let profileImage = document.getElementById("profile-pic");

	        inputFile.onchange = function () {
	            if (inputFile.files && inputFile.files[0]) {
	                let fileURL = URL.createObjectURL(inputFile.files[0]);
	                profileImage.src = fileURL;
	            }
	        };

	        function confirmSubmit() {
	            return confirm("Are you sure you want to save the changes?");
	        }
		</script>
	</div>
</body>
</html>
