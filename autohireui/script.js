...
data.forEach(car => {
    const carItem = document.createElement('div');
    carItem.classList.add('car-item'); // Add the CSS class
    carItem.innerHTML = `
        <h3>${car.manufacturer} ${car.model}</h3>
        <p>Year: ${car.year}</p>
        <p>Color: ${car.color}</p>
    `;
    carListElement.appendChild(carItem);
});
...

