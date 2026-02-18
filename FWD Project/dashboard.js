document.addEventListener("DOMContentLoaded", function () {

    // ===============================
    // 1️⃣ GET STORED DATA
    // ===============================

    let products = JSON.parse(localStorage.getItem("products")) || [];
    let transactions = JSON.parse(localStorage.getItem("transactions")) || [];

    // ===============================
    // 2️⃣ SAVE FUNCTIONS
    // ===============================

    function saveProducts() {
        localStorage.setItem("products", JSON.stringify(products));
    }

    function saveTransactions() {
        localStorage.setItem("transactions", JSON.stringify(transactions));
    }

    // ===============================
    // 3️⃣ AUTO UPDATE CARDS
    // ===============================

    function updateCards() {

        let totalSales = transactions.reduce((sum, tx) => sum + Number(tx.amount || 0), 0);
        let totalOrders = transactions.length;
        let totalProducts = products.length;
        let lowStock = products.filter(p => Number(p.stock) < 10).length;

        const cardValues = document.querySelectorAll(".card p");

        if (cardValues.length >= 4) {
            cardValues[0].innerText = "₹" + totalSales.toLocaleString();
            cardValues[1].innerText = totalOrders;
            cardValues[2].innerText = totalProducts;
            cardValues[3].innerText = lowStock + " Items";
        }
    }

    // ===============================
    // 4️⃣ AUTO LOAD TABLE
    // ===============================

    function loadTransactions() {

        const tableBody = document.querySelector("tbody");
        if (!tableBody) return;

        tableBody.innerHTML = "";

        transactions.forEach(tx => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>#${tx.id}</td>
                <td>${tx.customer}</td>
                <td class="${tx.status === 'Completed' ? 'completed' : 'pending'}">
                    ${tx.status}
                </td>
                <td>₹${tx.amount}</td>
            `;

            tableBody.appendChild(row);
        });
    }

    // ===============================
    // 5️⃣ ADD PRODUCT
    // ===============================

    window.addProduct = function (name, stock) {

        const newProduct = {
            id: Date.now(),
            name: name,
            stock: stock
        };

        products.push(newProduct);
        saveProducts();
        updateCards();
    };

    // ===============================
    // 6️⃣ ADD TRANSACTION
    // ===============================

    window.addTransaction = function (customer, amount, status) {

        const newTransaction = {
            id: Date.now(),
            customer: customer,
            amount: amount,
            status: status
        };

        transactions.push(newTransaction);
        saveTransactions();
        updateCards();
        loadTransactions();
    };

    // ===============================
    // 7️⃣ INITIAL AUTO LOAD
    // ===============================

    updateCards();
    loadTransactions();

});
