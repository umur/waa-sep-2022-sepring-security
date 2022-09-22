import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import './App.css';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Login from './components/Login';
import Signup from './components/Signup';
import ProductList from './components/ProductList';
import axios from 'axios';
import useToken from './components/hooks/useToken';

function App() {
  axios.defaults.baseURL = 'http://localhost:5000';
  axios.defaults.headers.post['Content-Type'] = 'application/json';

  const logout = () => {
    setToken(null);
  }

  const { token, setToken } = useToken();

  if (!token) {
    return (
      <Router>
        <div className='App'>
          <div className='main-content'>
            <div className='main-content-inner'>
              <Routes>
                <Route path='/signup' element={<Signup setToken={setToken} />} />
                <Route path='*' element={<Login setToken={setToken} />} />
              </Routes>
            </div>
          </div>
        </div>
      </Router>
    )
  }

  console.log('Has token')

  return (
    <Router>
      <div className='App'>
        <nav className='navbar navbar-expand-lg navbar-light fixed-top'>
          <div className='container'>
            <Link className='navbar-brand' to={'/'}>
              My App
            </Link>
            <div className='collapse navbar-collapse' id='navbarToggler'>
              <ul className='navbar-nav ml-auto'>
                <li className='nav-item'>
                  <Link className='nav-link' onClick={logout}>
                    Logout
                  </Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <div className='main-content'>
          <div className='main-content-inner'>
            <Routes>
              <Route exact path='/' element={<ProductList />} />
              <Route path='/signup' element={<Signup setToken={setToken} />} />
              <Route path='/products' element={<ProductList />} />
              <Route path='/*' element={<ProductList />} />
            </Routes>
          </div>
        </div>
      </div>
    </Router>
  );
}

export default App;
