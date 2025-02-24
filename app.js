document.addEventListener("DOMContentLoaded", function () {
    const productList = document.getElementById("product-list");
    const addProductForm = document.getElementById("add-product-form");
    const editProductForm = document.getElementById("edit-product-form");
    const cancelEditButton = document.getElementById("cancel-edit");

    // Función para cargar y mostrar los productos
    function loadProducts() {
        fetch("http://tudominio.com/public/index.php", {
            method: "GET"
        })
            .then(response => response.json())
            .then(data => {
                productList.innerHTML = ""; // Limpiar la lista
                data.registros.forEach(product => {
                    const li = document.createElement("li");
                    li.innerHTML = `
                        <span>${product.nombre} - ${product.descripcion} - $${product.precio}</span>
                        <div>
                            <button onclick="editProduct(${product.id}, '${product.nombre}', '${product.descripcion}', ${product.precio})">Editar</button>
                            <button onclick="deleteProduct(${product.id})">Eliminar</button>
                        </div>
                    `;
                    productList.appendChild(li);
                });
            })
            .catch(error => console.error("Error fetching products:", error));
    }

    // Función para agregar un producto
    addProductForm.addEventListener("submit", function (event) {
        event.preventDefault();
        const productName = document.getElementById("product-name").value;
        const productDescription = document.getElementById("product-description").value;
        const productPrice = document.getElementById("product-price").value;

        fetch("http://tudominio.com/public/index.php", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ nombre: productName, descripcion: productDescription, precio: productPrice })
        })
        .then(response => response.json())
        .then(data => {
            if (data.message === "Producto creado exitosamente.") {
                loadProducts(); // Recargar la lista de productos
                addProductForm.reset(); // Limpiar el formulario
            } else {
                console.error("Error adding product:", data.message);
            }
        })
        .catch(error => console.error("Error adding product:", error));
    });

    // Función para editar un producto
    window.editProduct = function (id, nombre, descripcion, precio) {
        document.getElementById("edit-product-id").value = id;
        document.getElementById("edit-product-name").value = nombre;
        document.getElementById("edit-product-description").value = descripcion;
        document.getElementById("edit-product-price").value = precio;
        editProductForm.style.display = "block";
    };

    // Función para actualizar un producto
    editProductForm.addEventListener("submit", function (event) {
        event.preventDefault();
        const productId = document.getElementById("edit-product-id").value;
        const productName = document.getElementById("edit-product-name").value;
        const productDescription = document.getElementById("edit-product-description").value;
        const productPrice = document.getElementById("edit-product-price").value;

        fetch("http://tudominio.com/public/index.php", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ id: productId, nombre: productName, descripcion: productDescription, precio: productPrice })
        })
        .then(response => response.json())
        .then(data => {
            if (data.message === "Producto actualizado exitosamente.") {
                loadProducts(); // Recargar la lista de productos
                editProductForm.reset(); // Limpiar el formulario
                editProductForm.style.display = "none"; // Ocultar el formulario de edición
            } else {
                console.error("Error updating product:", data.message);
            }
        })
        .catch(error => console.error("Error updating product:", error));
    });

    // Función para cancelar la edición
    cancelEditButton.addEventListener("click", function () {
        editProductForm.reset();
        editProductForm.style.display = "none";
    });

    // Función para eliminar un producto
    window.deleteProduct = function (id) {
        if (confirm("¿Estás seguro de que deseas eliminar este producto?")) {
            fetch("http://tudominio.com/public/index.php", {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ id: id })
            })
            .then(response => response.json())
            .then(data => {
                if (data.message === "Producto eliminado exitosamente.") {
                    loadProducts(); // Recargar la lista de productos
                } else {
                    console.error("Error deleting product:", data.message);
                }
            })
            .catch(error => console.error("Error deleting product:", error));
        }
    };

    // Cargar los productos al iniciar la página
    loadProducts();
});