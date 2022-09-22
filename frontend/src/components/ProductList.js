import axios from "axios"
import { useEffect, useState } from "react";
import useToken from "./hooks/useToken";



export default function ProductList(props) {
    const [products, setProducts] = useState([]);

    const { token } = useToken();

    const getProducts = async (token) => {
        const config = {
            headers: { Authorization: `Bearer ${token}` }
        };
        let result = await axios.get("/products", config);
        console.log('products: ', result.data);
        setProducts(result.data);
    }

    useEffect(() => {
        getProducts(token.accessToken);
    }, [token]);

    return (
        <div>
            Product List:
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Price</th>
                        <th scope="col">Rating</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map(p => (
                        <tr key={p.id}>
                            <th scope="row">{p.id}</th>
                            <td>{p.name}</td>
                            <td>{p.price}</td>
                            <td>{p.rating}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}