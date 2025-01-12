<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <!-- Include Sidebar -->
        <div th:replace="~{admin_sidebar :: admin_sidebar('userList')}"></div>
        <main class="content">
            <!-- Include Header -->
            <header class="header">
                <div class="header-right">
                    <div class="profile">
                        <img th:src="@{/img/profile.png}" alt="Admin Profile" class="profile-image">
                        <div class="header-profile">
                            <span class="profile-name">Moni Roy</span><br>
                            <span class="role">Admin</span>
                        </div>
                    </div>
                </div>
            </header>

            <h1>User List</h1>
            <!-- Filter Section -->
            <section class="filter-section">
                <div class="search-container">
                    <i class="bi bi-search search-icon"></i>
                    <input type="text" placeholder="Search..." class="header-search-bar" th:value="${searchTerm}">
                </div><br>
                <div class="filter-container">
                    <div class="filter-item">
                        <i class="bi bi-funnel"></i>
                        <span>Filter By</span>
                    </div>
                    <select class="filter-dropdown" th:value="${selectedDistrict}">
                        <option value="">District</option>
                        <option value="Ledang">Ledang</option>
                        <option value="Segamat">Segamat</option>
                        <option value="Muar">Muar</option>
                        <option value="Batu Pahat">Batu Pahat</option>
                        <option value="Kluang">Kluang</option>
                        <option value="Mersing">Mersing</option>
                        <option value="Pontian">Pontian</option>
                        <option value="Kulai Jaya">Kulai Jaya</option>
                        <option value="Kota Tinggi">Kota Tinggi</option>
                        <option value="Johor Bahru">Johor Bahru</option>
                    </select>
                    <button class="reset-button" th:onclick="|window.location.href='@{/admin/userList}'|">
                        <i class="bi bi-arrow-clockwise"></i> Reset Filter
                    </button>
                </div>
            </section>

            <!-- User List Section -->
            <section class="user-list">
                <table>
                    <thead>
                        <tr>
                            <th>User ID</th>
                            <th>Admin Name</th>
                            <th>School Name</th>
                            <th>District</th>
                            <th>YouTube Channel</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr th:each="user : ${userList}">
                            <td th:text="${user.id}"></td>
                            <td th:text="${user.fullName}"></td>
                            <td th:text="${user.schoolName}"></td>
                            <td th:text="${user.district}"></td>
                            <td>
                                <a th:href="${user.youtubeLink}" target="_blank" th:text="${user.youtubeLink}"></a>
                            </td>
                            <td>
							        <!-- View Icon -->
                                <button class="action-btn" th:onclick="|window.location.href='/TVPSS_Project/admin/user/view/${user.id}'|">
								    <i class="bi bi-eye"></i>
								</button>
									<!-- delete -->
								<button class="action-btn" th:onclick="|if(confirm('Are you sure you want to delete this user?')) window.location.href='@{/admin/user/delete/{id}(id=${user.id})}'|">
								    <i class="bi bi-trash"></i> <!-- Delete icon -->
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
