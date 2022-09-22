import { useState } from "react";

export default function useToken() {

    const saveToken = (token) => {
        if (token) {
            localStorage.setItem('tokens', JSON.stringify(token));
        } else {
            localStorage.clear('tokens');
        }
        setToken(token)
    }

    const getToken = () => {
        const tokens = localStorage.getItem('tokens');
        if (tokens) {
            return JSON.parse(tokens);
        }
        return null;
    }

    const [token, setToken] = useState(getToken());

    return {
        setToken: saveToken,
        token
    }
}