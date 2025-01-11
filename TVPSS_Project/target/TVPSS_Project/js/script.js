 // Search and Filter
   document.addEventListener("DOMContentLoaded", () => {
       const searchInput = document.querySelector(".header-search-bar");
       const filterDropdown = document.querySelector(".filter-dropdown");
       const resetButton = document.querySelector(".reset-button");
       const tableRows = document.querySelectorAll(".crew-list tbody tr");

       // Filter Rows
       function filterRows() {
           const searchTerm = searchInput.value.toLowerCase();
           const selectedRole = filterDropdown.value.toLowerCase();

           tableRows.forEach(row => {
               const name = row.querySelector("td:nth-child(1)").textContent.toLowerCase();
               const role = row.querySelector("td:nth-child(2)").textContent.toLowerCase();

               const matchesSearch = name.includes(searchTerm);
               const matchesFilter = !selectedRole || role === selectedRole;

               row.style.display = matchesSearch && matchesFilter ? "" : "none";
           });
       }

       // Event Listeners
       searchInput.addEventListener("input", filterRows);
       filterDropdown.addEventListener("change", filterRows);
       resetButton.addEventListener("click", () => {
           searchInput.value = "";
           filterDropdown.value = "";
           filterRows();
       });
   });