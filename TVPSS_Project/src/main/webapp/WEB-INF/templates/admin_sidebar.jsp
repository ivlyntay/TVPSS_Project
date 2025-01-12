<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="sidebar" th:fragment="admin_sidebar(activePage)">
    <div class="logo">
        <img th:src="@{/img/logo.png}" alt="TVPSS Logo" class="logo-image">
        TVPSS 7G
    </div>
    <ul class="menu">
        <li th:classappend="${activePage == 'dashboard'} ? 'active'">
            <a href="#"><i class="bi bi-speedometer2"></i> Dashboard</a>
        </li>
        <li th:classappend="${activePage == 'userList'} ? 'active'">
            <a th:href="@{/admin/user/userList}"><i class="bi bi-border-all"></i> User Management</a>
        </li>
        <li th:classappend="${activePage == 'contentManagement'} ? 'active'">
             <a href="#"><i class="bi bi-journal-text"></i> Content Management</a>
        </li>
        <li th:classappend="${activePage == 'programStatusManagement'} ? 'active'">
            <a href="#"><i class="bi bi-bar-chart"></i> Program Status Management</a>
        </li>
        <li th:classappend="${activePage == 'crewList'} ? 'active'">
            <a th:href="@{/admin/crew/crewList}"><i class="bi bi-people"></i> Crew Management</a>
        </li>
        <li th:classappend="${activePage == 'profile'} ? 'active'">
            <a th:href="@{/admin/profile/profile}"><i class="bi bi-person"></i> Profile</a>
        </li>
        <li th:classappend="${activePage == 'settings'} ? 'active'">
            <a th:href="@{/admin/profile/settings}"><i class="bi bi-gear"></i> Settings</a>
        </li>
        <li class="separator"></li>
        <li class="logout"><a th:href="@{/login}"><i class="bi bi-box-arrow-right"></i> Logout</a></li>
    </ul>
</div>

</body>
</html>
