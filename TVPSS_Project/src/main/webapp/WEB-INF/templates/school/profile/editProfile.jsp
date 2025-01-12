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
		<!-- Include the sidebar (assuming it's managed with Thymeleaf, this will be included) -->
		<div th:replace="~{school_sidebar :: school_sidebar('profile')}"></div>

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
				<form th:action="@{/school/profile/updateProfile}" method="post" enctype="multipart/form-data" onsubmit="return confirmSubmit();">
					<input type="hidden" name="id" th:value="${user.id}">
					<div class="form-grid">
						<!-- Row 1 -->
						<div class="form-group">
							<label for="fullName">Admin Name (as per IC)</label>
							<input type="text" id="fullName" name="fullName" th:value="${user.fullName}" required>
						</div>
						<div class="form-group">
							<label for="icNumber">IC Number</label>
							<input type="text" id="icNumber" name="icNumber" th:value="${user.icNumber}" required>
						</div>
						
						<!-- Row 2 -->
						<div class="form-group">
							<label for="schoolName">School Name</label>
							<input type="text" id="schoolName" name="schoolName" th:value="${user.schoolName}" required>
						</div>
						<div class="form-group">
							<label for="email">Email</label>
							<input type="email" id="email" name="email" th:value="${user.email}" required>
						</div>
						
						<!-- Row 3 -->
						<div class="form-group">
							<label for="contactNumber">Contact Number</label>
							<input type="text" id="contactNumber" name="contactNumber" th:value="${user.contactNumber}" required>
						</div>
						<div class="form-group">
							<label for="district">District</label>
							<select id="district" name="district" required>
								<option value="">Select District</option>
								<option th:value="ledang" th:selected="${user.district == 'ledang'}">Ledang</option>
								<option th:value="segamat" th:selected="${user.district == 'segamat'}">Segamat</option>
								<option th:value="muar" th:selected="${user.district == 'muar'}">Muar</option>
								<option th:value="batupahat" th:selected="${user.district == 'batupahat'}">Batu Pahat</option>
								<option th:value="kluang" th:selected="${user.district == 'kluang'}">Kluang</option>
								<option th:value="mersing" th:selected="${user.district == 'mersing'}">Mersing</option>
								<option th:value="pontian" th:selected="${user.district == 'pontian'}">Pontian</option>
								<option th:value="kulaijaya" th:selected="${user.district == 'kulaijaya'}">Kulai Jaya</option>
								<option th:value="kotatinggi" th:selected="${user.district == 'kotatinggi'}">Kota Tinggi</option>
								<option th:value="johorbahru" th:selected="${user.district == 'johorbahru'}">Johor Bahru</option>
							</select>
						</div>
						
						<!-- Row 4 -->
				        <div class="form-group">
				            <label for="youtubeLink">YouTube Channel Link</label>
				            <input type="text" id="youtubeLink" name="youtubeLink" th:value="${user.youtubeLink}">
				        </div>
				        <div class="form-group">
				            <label for="youtubeChannelName">YouTube Channel Name</label>
				            <input type="text" id="youtubeChannelName" name="youtubeChannelName" th:value="${user.youtubeChannelName}">
				        </div>
					</div>

					<!-- Submit Button -->
					<div class="form-buttons">
						<button type="submit" class="btn btn-primary">Save Changes</button>
						<button type="button" class="btn btn-secondary" th:onclick="|window.location.href='@{/school/profile/profile}'|">Cancel</button>					
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