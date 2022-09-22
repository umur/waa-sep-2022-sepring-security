import axios from "axios";
import { useState } from "react";
import PropTypes from "prop-types";
import { Link, useNavigate } from "react-router-dom";

export default function Signup(props) {
    const [firstName, setFirstName] = useState();
    const [lastName, setLastName] = useState();
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const nav = useNavigate();

    async function signup(credentials) {
        let result = await axios.post('/uaa/signup', credentials);
        return result.data;
    }

    const handleSubmit = async e => {
        e.preventDefault();
        const token = await signup({
            firstName,
            lastName,
            email,
            password
        });
        props.setToken(token);
        nav("/");
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <h3>Sign Up</h3>
                <div className="mb-3">
                    <label>First Name</label>
                    <input type="text" name="firstName"
                        className="form-control"
                        placeholder="First Name"
                        onChange={e => setFirstName(e.target.value)} />
                </div>
                <div className="mb-3">
                    <label>Last Name</label>
                    <input type="text"
                        name="lastName"
                        className="form-control"
                        placeholder="Last Name"
                        onChange={e => setLastName(e.target.value)} />
                </div>
                <div className="mb-3">
                    <label>Email</label>
                    <input type="email"
                        name="email"
                        className="form-control"
                        placeholder="Email"
                        onChange={e => setEmail(e.target.value)} />
                </div>
                <div className="mb-3">
                    <label>Password</label>
                    <input type="password"
                        name="password"
                        className="form-control"
                        placeholder="Password"
                        onChange={e => setPassword(e.target.value)} />
                </div>
                <div className="d-grid">
                    <button type="submit" className="btn btn-primary">Sign Up</button>
                </div>
                <p className="signup text-right">
                    Already had an account. <Link to="/login">Login</Link>
                </p>
            </form>
        </div>
    )
}

Signup.propTypes = {
    setToken: PropTypes.func.isRequired
}