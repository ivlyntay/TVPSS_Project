<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Program Status</title>
    <link th:href="@{/css/program.css}" rel="stylesheet" />
    <link th:href="@{/css/progSidebar_header.css}" rel="stylesheet" />
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <!-- Include Sidebar -->
         <div th:replace="~{school_sidebar :: school_sidebar('ProgramStatus')}"></div>

        <main class="content">
            <!-- Include Header -->
           <!--   <div th:replace="~{header_school :: header}"></div>-->

            <header class="header">
                <div class="header-right">
                    <div class="profile">
                        <img th:src="@{/img/profile.png}" alt="Profile Image" class="profile-image">
                        <div class="header-profile">
                            <span class="profile-name">Ali bin Abu</span><br>
                            <span class="role">schoolAdmin</span>
                        </div>
                    </div>
                </div>
            </header>

            <h1>Program Status</h1>
			<h3>School Information</h3>
			<div class="card">
			    <div class="school-info">
			        <div>
			            <div class="form-group">
			                <label>School Name:</label>
			                <span th:text="${school.schoolName}">SMK KEBANGSAAN MAWAI</span>
			            </div></div></div></div>
            <!-- Program Status Section -->
            <section class="program-status-section">
            
                <h3>Program Status</h3>
                <div class="card">
                    <form id="programStatusForm" th:action="@{/school/program/program-status/save}" method="post">
						<input type="hidden" name="schoolName" th:value="${school.schoolName}" />
						<!-- Add hidden version field -->
					    <input type="hidden" name="statusVersion" th:value="${program.statusVersion}" />
					    
					    <!-- Add hidden equipment level field with a default value -->
					    <input type="hidden" name="equipmentLevel" th:value="${program.equipmentLevel != null ? program.equipmentLevel : '1'}" />
                        <!-- Logo Status -->
                        <div class="form-group">
                            <label>Logo:</label>
                            <div class="radio-group">
                                <label>
                                    <input type="radio" name="logoStatus" value="true" th:checked="${program.logoStatus}"/> Yes
                                </label>
                                <label>
                                    <input type="radio" name="logoStatus" value="false" th:checked="${!program.logoStatus}"/> No
                                </label>
                            </div>
                        </div>

                        <!-- Studio Status -->
                        <div class="form-group">
                            <label>Studio/Mini Studio:</label>
                            <div class="radio-group">
                                <label>
                                    <input type="radio" name="studioStatus" value="true" th:checked="${program.studioStatus}"/> Yes
                                </label>
                                <label>
                                    <input type="radio" name="studioStatus" value="false" th:checked="${!program.studioStatus}"/> No
                                </label>
                            </div>
                        </div>

                        <!-- Recording Status -->
                        <div class="form-group">
                            <label>Recording Status:</label>
                            <div class="radio-group">
                                <label>
                                    <input type="radio" name="recordingStatus" value="true" th:checked="${program.recordingStatus}"/> Yes
                                </label>
                                <label>
                                    <input type="radio" name="recordingStatus" value="false" th:checked="${!program.recordingStatus}"/> No
                                </label>
                            </div>
                        </div>

                        <!-- YouTube Status -->
                        <div class="form-group">
                            <label>Upload on YouTube:</label>
                            <div class="radio-group">
                                <label>
                                    <input type="radio" name="youtubeStatus" value="true" th:checked="${program.youtubeStatus}"/> Yes
                                </label>
                                <label>
                                    <input type="radio" name="youtubeStatus" value="false" th:checked="${!program.youtubeStatus}"/> No
                                </label>
                            </div>
                        </div>

                        <!-- YouTube Channel Link -->
                        <div class="form-group">
                            <label>YouTube Channel Link:</label>
                            <input type="text" name="youtubeLink" placeholder="Enter YouTube link" class="form-control" th:value="${program.youtubeLink}" />
                        </div>

                        <!-- Inside/Outside Status -->
                        <div class="form-group">
                            <label>Inside/Outside Status:</label>
                            <div class="radio-group">
                                <label>
                                    <input type="radio" name="insideOutsideStatus" value="true" th:checked="${program.insideOutsideStatus}"/> Inside
                                </label>
                                <label>
                                    <input type="radio" name="insideOutsideStatus" value="false" th:checked="${!program.insideOutsideStatus}"/> Outside
                                </label>
                            </div>
                        </div>

                        <!-- Collaborate Status -->
                        <div class="form-group">
                            <label>Collaborate Status:</label>
                            <div class="radio-group">
                                <label>
                                    <input type="radio" name="collaborateStatus" value="true" th:checked="${program.collaborateStatus}"/> Yes
                                </label>
                                <label>
                                    <input type="radio" name="collaborateStatus" value="false" th:checked="${!program.collaborateStatus}"/> No
                                </label>
                            </div>
                        </div>

                        <!-- Green Screen Technology -->
                        <div class="form-group">
                            <label>Using 'Green Screen' Technology:</label>
                            <div class="radio-group">
                                <label>
                                    <input type="radio" name="greenScreenStatus" value="true" th:checked="${program.greenScreenStatus}"/> Yes
                                </label>
                                <label>
                                    <input type="radio" name="greenScreenStatus" value="false" th:checked="${!program.greenScreenStatus}"/> No
                                </label>
                            </div>
                        </div>

                        <!-- Version Display -->
                        <div class="form-group">
                            <label>Version:</label>
                            <span id="version-display" th:text="${program.statusVersion}">1</span>
                        </div>

                        <!-- Buttons -->
                        <div class="button-container">
                            <button type="submit" class="btn btn-primary save-btn">Save</button>
                            <button type="button" class="btn btn-secondary next-btn" th:onclick="|window.location.href='@{/school/program/equipment_management}'|">Next</button>
                        </div>
                        
                    </form>
                </div>
            </section>
        </main>
    </div>

    <script>
		document.addEventListener('DOMContentLoaded', function () {
		    const form = document.getElementById('programStatusForm');
		    
		    form.addEventListener('submit', function (event) {
		        event.preventDefault();
		        
		        // Create FormData object
		        const formData = new FormData(form);
		        
		        // Send AJAX request
		        fetch(form.action, {
		            method: 'POST',
		            body: formData,
		            headers: {
		                'Accept': 'application/json, text/plain'
		            }
		        })
		        .then(response => response.text())
		        .then(result => {
		            console.log('Server response:', result); // Debug logging
		            if (result === 'success') {
		                alert('Program status saved successfully!');
		                // Update version if needed
		                updateVersion();
		            } else {
		                alert('Error saving program status: ' + result);
		            }
		        })
		        .catch(error => {
		            console.error('Error:', error);
		            alert('Error saving program status: ' + error);
		        });
		    });
		    
		    function updateVersion() {
		        const formData = new FormData(form);
		        let version = 1;
		        if (formData.get('logoStatus') === 'true') version = 2;
		        if (formData.get('studioStatus') === 'true' && formData.get('recordingStatus') === 'true') version = 3;
		        if (formData.get('youtubeStatus') === 'true' && formData.get('collaborateStatus') === 'true' && 
		            formData.get('greenScreenStatus') === 'true') version = 4;
		        document.getElementById('version-display').textContent = version;
		    }
		});
		</script>
</body>
</html>
