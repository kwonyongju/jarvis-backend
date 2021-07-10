const formatDataToCart = ({ items, nameLabel }) => {
  const map = new Map();
  const temp = [];

  items.forEach((item) => {
    const cur = item[nameLabel];

    if (!map.has(cur)) map.set(cur, 1);
    else map.set(cur, map.get(cur) + 1);
  });

  let index = 0;
  let totalPrice = 0;

  map.forEach((value, key) => {
    const item = items.find((i) => i[nameLabel] === key);
    const price = item.quantity
      ? (item.price * item.quantity).toFixed(2)
      : (item.price * value).toFixed(2);

    temp.push({
      index: ++index,
      name: item[nameLabel],
      quantity: item.quantity || value,
      price: price,
      tax: (price * 0.05).toFixed(2),
    });

    totalPrice += parseFloat(price) * 1.05;
  });

  temp.push({
    totalPrice: `$${totalPrice.toFixed(2)}`,
  });

  return temp;
};

module.exports = {
  formatDataToCart,
};
