import React, { useState, useEffect } from 'react';

const Categories = () => {
    const [categories, setCategories]=useState([]);
    useEffect(() => {
        fetch('http://localhost:9091/api/categories')
            .then(response => response.json())
            .then(data => setCategories(data))
            .catch(error => console.error('Error fetching categories:', error));
    }, []);
    return ( 
        <div>
            <h2>Categories</h2>
        <ul className="list-group">
            {categories.map((category, index)=>(
                <li className="list-group-item" key={index}>{category}</li>
            ))}
        </ul> 
        </div>
     );
}
 
export default Categories;