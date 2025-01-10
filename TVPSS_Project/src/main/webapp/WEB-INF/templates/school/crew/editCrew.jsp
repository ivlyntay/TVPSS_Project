<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Crew</title>
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <!-- Sidebar Include -->
        <div th:replace="~{school_sidebar :: school_sidebar('crewList')}"></div>
        
        <!-- Main Content -->
        <main class="content">
            <!-- Header Include -->
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

            <h1>Edit Crew</h1>
            <div class="form-container">
            <!-- Display Success Message -->
		        <div th:if="${message}" class="alert alert-success">
		            <p th:text="${message}"></p>
		        </div>
                <form th:action="@{/school/crew/update}" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" th:value="${crew.id}">
                    
                    <!-- Profile Image Upload -->
                    <div class="image-upload">
					    <!-- Display the current crew photo or default photo if not set -->
					    <img th:src="@{/crew_photos/{photo}(photo=${crew.photo != null ? crew.photo : 'default.jpg'})}" 
					         alt="Crew Profile" 
					         class="crew-image">
					    
					    <label for="crew-photo" class="upload-label">Update Crew Photo</label>
                        <input type="file" id="crew-photo" name="crewPhoto" style="opacity: 0" accept="image/*">
					</div>
                    <div class="form-grid">
                        <!-- Row 1 -->
                        <div class="form-group">
                            <label for="fullName">Full Name (as per IC)</label>
                            <input type="text" id="fullName" name="fullName" 
                                   th:value="${crew.fullName}" 
                                   placeholder="Enter full name">
                        </div>
                        <div class="form-group">
                            <label for="icNumber">IC Number</label>
                            <input type="text" id="icNumber" name="icNumber" 
                                   th:value="${crew.icNumber}" 
                                   placeholder="Enter IC Number">
                        </div>

                        <!-- Row 2 -->
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" 
                                   th:value="${crew.email}" 
                                   placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <label for="contactNumber">Contact Number</label>
                            <input type="text" id="contactNumber" name="contactNumber" 
                                   th:value="${crew.contactNumber}" 
                                   placeholder="Enter contact number">
                        </div>

                        <div class="form-group">
                            <label for="gender">Gender</label>
                            <select id="gender" name="gender">
                                <option value="">Select Gender</option>
                                <option value="male" th:selected="${crew.gender == 'male'}">Male</option>
                                <option value="female" th:selected="${crew.gender == 'female'}">Female</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="role">Role</label>
                            <select id="role" name="role">
							    <option value="">Select Role</option>
							    <option value="Reporter" th:selected="${crew.role == 'Reporter'}">Reporter</option>
							    <option value="Assistant Director" th:selected="${crew.role == 'Assistant Director'}">Assistant Director</option>
							    <option value="Camera Operator" th:selected="${crew.role == 'Camera Operator'}">Camera Operator</option>
							    <option value="Script Writer" th:selected="${crew.role == 'Script Writer'}">Script Writer</option>
							    <option value="Video Editor" th:selected="${crew.role == 'Video Editor'}">Video Editor</option>
							</select>
                        </div>

                        <!-- Form Buttons -->
                        <div class="form-buttons">
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                             <button type="button" class="btn btn-secondary" th:onclick="|window.location.href='@{/school/crew/crewList}'|">Cancel</button>
                        </div>
                    </div>
                </form>
            </div>
        </main>
    </div>
    <script> 
    // Get references to elements
    let inputFile = document.getElementById("crew-photo");
    let crewImage = document.querySelector(".crew-image");
    let cameraIcon = document.getElementById("camera-icon");

    // Handle file input change (when a file is selected)
    inputFile.onchange = function() {
        if (inputFile.files && inputFile.files[0]) {
            // Create a URL for the selected file
            let fileURL = URL.createObjectURL(inputFile.files[0]);

            // Update the image preview
            crewImage.src = fileURL;
            crewImage.style.display = 'block'; // Show the image preview

            // Hide the camera icon once the image is selected
            cameraIcon.style.display = 'none';
        }
    }

    // Optional: Add functionality to show the camera icon again when no file is selected
    inputFile.onfocus = function() {
        cameraIcon.style.display = 'block'; // Show camera icon when input is focused
    }
</script>

</body>
</html>
