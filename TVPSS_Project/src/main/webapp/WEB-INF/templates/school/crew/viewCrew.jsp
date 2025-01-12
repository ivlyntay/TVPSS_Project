<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Crew Profile</title>
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <!-- Include Sidebar -->
        <div th:replace="~{school_sidebar :: school_sidebar('crewList')}"></div>

        <main class="content">
            <!-- Header Section -->
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

            <!-- View Crew Profile Section -->
            <h1>View Crew Profile</h1>
            <section class="view-crew-profile">
                <div class="form-container">
                    <div class="image-upload">
                        <!-- Display the crew photo dynamica
					     alt="Crew Profile" lly -->
                       <img th:src="@{/crew_photos/{photo}(photo=${crew.photo != null && crew.photo != '' ? crew.photo : 'default.jpg'})}" 
					     class="crew-image" />
                        <h2 th:text="${crew.fullName}"></h2>
                    </div>
                    <div class="profile-details">
                        <table>
                            <tr>
                                <td>IC Number:</td>
                                <td th:text="${crew.icNumber}"></td>
                            </tr>
                            <tr>
                                <td>Email:</td>
                                <td th:text="${crew.email}"></td>
                            </tr>
                            <tr>
                                <td>Contact Number:</td>
                                <td th:text="${crew.contactNumber}"></td>
                            </tr>
                            <tr>
                                <td>Gender:</td>
                                <td th:text="${crew.gender}"></td>
                            </tr>
                            <tr>
                                <td>Role:</td>
                                <td th:text="${crew.role}"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="form-buttons">
                        <button type="button" class="btn btn-primary" th:onclick="|window.location.href='@{/school/crew/crewList}'|">Back</button>
                    </div>
                </div>
            </section>
        </main>
    </div>
</body>
</html>
