const formatTableData = (items) => {
  const map = new Map();
  const temp = [];

  items.forEach((item) => {
    if (!map.has(item.name)) map.set(item.name, 1);
    else map.set(item.name, map.get(item.name) + 1);
  });

  let index = 0;
  let totalPrice = 0;
  map.forEach((value, key) => {
    const item = items.find((i) => i.name === key);
    const price = (item.price * value).toFixed(2);

    temp.push({
      index: ++index,
      name: item.name,
      quantity: value,
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
  formatTableData,
};
