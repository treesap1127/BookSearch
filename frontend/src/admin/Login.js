import React, { useState, useRef , useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './style/login.css';

const Login = ({ onLoginSuccess }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const usernameInputRef = useRef(null);
    const passwordInputRef = useRef(null);

    const [ipInfo, setIpInfo] = useState({ ipAllow: false, ip: '127.0.0.1' });
    const navigate = useNavigate();
    
    useEffect(() => {
        const fetchData = async () => {
          try {
            const response = await axios.get('/api/ipCheck');
            setIpInfo(response.data);
            if (!response.data.ipAllow) {
                navigate('/');
              }
          } catch (error) {
            console.error('Error fetching data:', error);
          }
        };
    
        fetchData();
      }, [navigate]);

    const handleKeyPress = (e) => {
        if (e.key === 'Enter') {
            handleLogin();
        }
    };

    const handleLogin = async () => {
        try {
            // Perform any client-side validation if needed
            if (!validateInput(username) || !validateInput(password)) {
                alert('아이디 및 비밀번호 입력을 해주세요.');
                if (!validateInput(username)) {
                    usernameInputRef.current.focus();
                } else {
                    passwordInputRef.current.focus();
                }
                return;
            }

            // Send login credentials to the server
            const response = await axios.post('/api/admin/auth', {
                username,
                password,
            });

            if (response.status === 200) {
                localStorage.setItem('jwtToken', response.data.token);
                navigate('/admin');
            }
            else {
                throw new Error(response.statusText);
            }
        } catch (error) {
            if (error.response.status === 401) {
                alert('로그인 정보가 일치하지 않습니다.');
              }
            else if (error.response.status === 406) {
                alert('로그인 권한이 올바르지 않습니다.');  
            }
            else {
                console.error(error);
                alert('Login failed');
              }
        }
    };
    
    const validateInput = (input) => {
        return input !== null && input.trim() !== '';
    };

    return (
        <div className="login_container" >
            <div className="login-box">
                <div className="ipBox">
                    <p className="ipText">[ {ipInfo.ip} ]</p>
                </div>
                <div className="login_shadow">
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        ref={usernameInputRef}
                        onKeyDown={(e) => handleKeyPress(e)}
                        className="custom-input"
                        placeholder="Username"
                    />

                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        ref={passwordInputRef}
                        onKeyDown={(e) => handleKeyPress(e)}
                        className="custom-input"
                        placeholder="Password"
                    />
                </div>
                <button className='btn' onClick={handleLogin}>Login</button>
            </div>
        </div>
    );
};

export default Login;
