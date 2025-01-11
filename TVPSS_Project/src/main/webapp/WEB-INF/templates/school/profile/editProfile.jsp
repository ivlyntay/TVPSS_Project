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
		<div th:replace="sidebar_profile :: sidebar"></div>

		<!-- Main Content -->
		<main class="content">
			<!-- Include the header -->
			<div th:replace="../header_school :: header"></div>

			<h1>Edit Profile</h1>
			<div class="form-container">
				<!-- Profile Image Upload -->
				<div class="image-upload">
					<div class="image-circle" id="image-circle">
						<img src="../../img/profile.png" id="profile-pic">
					</div>
					<label for="crew-photo" class="upload-label">Upload Profile Picture</label> 
					<input type="file" id="crew-photo" name="crewPhoto" style="opacity: 0" accept="image/*">	
				</div>

				<!-- Form Fields (Editable) -->
				<form th:action="@{/school/updateProfile}" method="post">
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
							<label for="ytbLink">YouTube Channel Link</label>
							<input type="text" id="ytbLink" name="ytbLink" th:value="${user.youtubeLink}">
						</div>
						<div class="form-group">
							<label for="ytbName">YouTube Channel Name</label>
							<input type="text" id="ytbName" name="ytbName" th:value="${user.youtubeChannelName}">
						</div>
					</div>

					<!-- Submit Button -->
					<div class="form-buttons">
						<button type="submit" class="btn btn-primary">Save Changes</button>
						<button type="button" class="btn btn-secondary" th:onclick="'window.location.href=\'/school/profile\''">Cancel</button>
					</div>
				</form>
			</div>
		</main>
		<script src="../../js/script.js"></script>
	</div>
</body>
</html>
