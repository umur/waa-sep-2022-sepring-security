import axios from "axios";
import { useState } from "react"
import PropTypes from 'prop-types'
import { Link } from "react-router-dom";

async function login(credentials) {
    let result = await axios.post('/uaa/login', credentials);
    return result.data;
}

export default function Login(props) {
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();

    const handleSubmit = async e => {
        e.preventDefault();
        const token = await login({
            email,
            password
        });
        props.setToken(token);
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <h3>Log In</h3>
                <div className="mb-3">
                    <label>Email Address</label>
                    <input type="email" className="form-control"
                        placeholder="Email"
                        onChange={e => setEmail(e.target.value)} />
                </div>
                <div className="mb-3">
                    <label>Password</label>
                    <input type="password" className="form-control"
                        placeholder="Password"
                        onChange={e => setPassword(e.target.value)} />
                </div>
                <div className="d-grid">
                    <button type="submit" className="btn btn-primary">
                        Login
                    </button>
                    <p className="signup text-right">
                        <Link to="/signup">Sign Up</Link>
                    </p>
                </div>
            </form>
        </div>
    )
}

Login.propTypes = {
    setToken: PropTypes.func.isRequired
};