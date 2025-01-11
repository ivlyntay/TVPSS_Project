<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crew List</title>
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <!-- Include Sidebar -->
        <div th:replace="~{admin_sidebar :: admin_sidebar('crewList')}"></div>
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
            
            <h1>Crew List</h1>
            <!-- Filter Section -->
            <section class="filter-section">
                <div class="search-container">
                    <i class="bi bi-search search-icon"></i> <!-- Search Icon -->
                    <input type="text" placeholder="Search..." class="header-search-bar" th:value="${searchTerm}">
                </div><br>
                <div class="filter-container">
                    <div class="filter-item">
                        <i class="bi bi-funnel"></i> <!-- Filter Icon -->
                        <span>Filter By</span>
                    </div>
                    <select class="filter-dropdown" th:value="${selectedRole}">
                        <option value="">Role</option>
                        <option value="Reporter">Reporter</option>
                        <option value="Assistant Director">Assistant Director</option>
                        <option value="Camera Operator">Camera Operator</option>
                        <option value="Script Writer">Script Writer</option>
                        <option value="Video Editor">Video Editor</option>
                    </select>
                    <select class="filter-dropdown" th:value="${selectedSchool}">
                        <option value="">School</option>
                        <option value="SMK Bandar Kota Tinggi">SMK Bandar Kota Tinggi</option>
                        <!-- Add more options dynamically as needed -->
                    </select>
                    <button class="reset-button" th:onclick="|window.location.href='@{/admin/crewList}'|">
                        <i class="bi bi-arrow-clockwise"></i> Reset Filter
                    </button>
                </div>
            </section>

            <!-- Crew List Section -->
            <section class="crew-list">
                <table>
                    <thead>
                        <tr>
                            <th>School Name</th>
                            <th>Crew Name</th>
                            <th>Role</th>
                            <th>Email</th>
                            <th>Contact Number</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr th:each="crew : ${crewList}">
                            <td th:text="${crew.user.schoolName}">SMK Bandar Kota Tinggi</td>
                            <td th:text="${crew.fullName}">Ivlyn Tay Wan Rou</td>
                            <td th:text="${crew.role}">Script Writer</td>
                            <td th:text="${crew.email}">ivlyn@graduate.utm.my</td>
                            <td th:text="${crew.contactNumber}">011-10746482</td>
                            <td>
                                <!-- View Icon -->
                                <button class="action-btn" th:onclick="|window.location.href='@{/admin/viewCrew/{id}(id=${crew.id})}'|">
                                    <i class="bi bi-eye"></i> <!-- View icon -->
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </section>
        </main>
    </div>

   <script th:src="@{/js/script.js}"></script>
</body>
</html>
