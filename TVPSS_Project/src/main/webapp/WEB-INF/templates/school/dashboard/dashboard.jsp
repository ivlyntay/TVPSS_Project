<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>School Dashboard</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css" rel="stylesheet">
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
    <link rel="stylesheet" href="../../css/content.css">
    <link rel="stylesheet" href="../../css/sidebar_header.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-gray-50">
    <div class="container">
        <div th:replace="~{school_sidebar :: school_sidebar('dashboard')}"></div>
        
        <div class="main-content p-6">
            <!-- School Header -->
            <div class="mb-8">
                <h1 class="text-2xl font-bold text-gray-800" th:text="${schoolName} + ' Dashboard'">School Dashboard</h1>
            </div>

            <!-- Stats Grid -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mb-8">
                <div class="bg-white rounded-xl shadow-lg p-6">
                    <div class="flex items-center">
                        <div class="p-3 rounded-full bg-blue-100 text-blue-500">
                            <i class="bi bi-collection"></i>
                        </div>
                        <div class="ml-4">
                            <h3 class="text-gray-500 text-sm">Total Content</h3>
                            <p class="text-2xl font-semibold text-gray-700" th:text="${totalContent}">0</p>
                        </div>
                    </div>
                </div>

                <div class="bg-white rounded-xl shadow-lg p-6">
                    <div class="flex items-center">
                        <div class="p-3 rounded-full bg-purple-100 text-purple-500">
                            <i class="bi bi-people"></i>
                        </div>
                        <div class="ml-4">
                            <h3 class="text-gray-500 text-sm">Crew Members</h3>
                            <p class="text-2xl font-semibold text-gray-700" th:text="${totalCrewMembers}">0</p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Content Analytics -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mb-8">
                <!-- Content by Category -->
                <div class="bg-white rounded-xl shadow-lg p-6">
                    <h3 class="text-lg font-semibold mb-4">Content by Category</h3>
                    <table class="min-w-full">
                        <thead>
                            <tr>
                                <th class="text-left py-2">Category</th>
                                <th class="text-right py-2">Count</th>
                                <th class="text-right py-2">Percentage</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr th:each="category : ${contentByCategory}">
                                <td class="py-2" th:text="${category.key}">Category</td>
                                <td class="text-right py-2" th:text="${category.value}">0</td>
                                <td class="text-right py-2" 
                                    th:text="${#numbers.formatDecimal(category.value * 100.0 / totalContent, 1, 1) + '%'}">
                                    0%
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Recent Uploads -->
            <div class="bg-white rounded-xl shadow-lg p-6">
                <h3 class="text-lg font-semibold mb-4">Recent Uploads</h3>
                <div class="space-y-4">
                    <div th:each="content : ${recentUploads}" 
                         class="flex items-center justify-between p-4 hover:bg-gray-50 rounded-lg">
                        <div class="flex items-center">
                            <div class="p-2 rounded-full bg-blue-100 mr-4">
                                <i class="bi bi-play-circle text-blue-500"></i>
                            </div>
                            <div>
                                <p class="font-medium text-gray-800" th:text="${content.videoTitle}">Video Title</p>
                                <p class="text-sm text-gray-500" th:text="${#temporals.format(content.uploadDate, 'MMM dd, yyyy')}">Upload Date</p>
                            </div>
                        </div>
                        <span class="px-3 py-1 text-sm text-blue-600 bg-blue-100 rounded-full" th:text="${content.category}">Category</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>