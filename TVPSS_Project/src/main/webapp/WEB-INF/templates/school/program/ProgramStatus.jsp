<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Program Status</title>
    <link th:href="@{/css/program.css}" rel="stylesheet" />
    <link th:href="@{/css/progSidebar_header.css}" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <!-- Include Sidebar -->
        <div th:replace="~{school_sidebar :: school_sidebar('ProgramStatus')}"></div>

        <main class="content">
            <!-- Include Header -->
            <div th:replace="~{header_school :: header}"></div>

            <header class="header">
                <div class="header-right">
                    <div class="profile">
                        <img th:src="@{/img/profile.png}" alt="Profile Image" class="profile-image">
                        <div class="header-profile">
                            <span class="profile-name">Moni Roy</span><br>
                            <span class="role">Admin</span>
                        </div>
                    </div>
                </div>
            </header>

            <h1>Program Status</h1>

            <!-- Program Status Section -->
            <section class="program-status-section">
                <h3>Program Status</h3>
                <div class="card">
                    <form id="programStatusForm" th:action="@{/program-status/save}" method="post">

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
                            <button type="button" class="btn btn-secondary next-btn" th:onclick="|window.location.href='@{/TVPSS_Project/school/program/equipment_management}'|">Next</button>
                        </div>
                        
                    </form>
                </div>
            </section>
        </main>
    </div>

    <script>
    document.addEventListener('DOMContentLoaded', function () {
        const saveBtn = document.querySelector('.save-btn');
        const form = document.getElementById('programStatusForm');
        const versionDisplay = document.getElementById('version-display');

        saveBtn.addEventListener('click', function (event) {
            // Prevent form submission
            event.preventDefault();

            // Collect form data
            const formData = new FormData(form);
            const data = Object.fromEntries(formData.entries());

            // Calculate version
            let version = 1;
            if (data.logoStatus === 'true') version = 2;
            if (data.studioStatus === 'true' && data.recordingStatus === 'true') version = 3;
            if (data.youtubeStatus === 'true' && data.collaborateStatus === 'true' && data.greenScreenStatus === 'true') version = 4;

            // Update version display
            versionDisplay.textContent = version;
        });
    });
    </script>
</body>
</html>
