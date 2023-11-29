import React, { useState , useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../style/admin.css'


const Admin = ({ onUploadSuccess }) => {
    const [selectedFile, setSelectedFile] = useState(null);
    const [uploadName, setUploadName] = useState('');
    const [indices, setIndices] = useState([]);
    const [showFileContainer, setShowFileContainer] = useState(true);
    const [showIndexListContainer, setShowIndexListContainer] = useState(false);
    const [loading, setLoading] = useState(false);

    const fetchIndices = async () => {
        try {
          const response = await axios.get('http://localhost:9200/_cat/indices?v');
          const indexData = response.data.split('\n').map((line) => line.split(/\s+/));
          setIndices(indexData.slice(1, -1)); // 첫 번째와 마지막은 헤더와 빈 줄이므로 제외
        } catch (error) {
          console.error('Error fetching index data:', error);
        }
      };

    const isCSV = (file) => {
        return (
            file.type === 'text/csv' ||
            file.name.toLowerCase().endsWith('.csv')
        );
    };

    const handleFileChange = (event) => {
        const file = event.target.files[0];

        if (isCSV(file)) {
            setSelectedFile(event.target.files[0]);
            setUploadName(file.name);
        }
        else{
            alert('CSV 파일만 등록 가능합니다.');
            return false;
        }
    };

    const autoIndex = async () => {
        if (selectedFile) {
            try {
                setLoading(true); // 로딩 시작
                const formData = new FormData();
                formData.append('excelFile', selectedFile);

                const response = await axios.post('/api/book/bookUpload', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                    },
                });
                
                if (response.status === 200) {
                    alert(response.data);
                }
            } catch (error) {
                console.error(error);
            } finally {
                setLoading(false); // 로딩 종료
              }
        }
        else{
            alert('파일을 등록해주세요');
            return 
        }
    };

    const deleteIndex = async () => {
        try {
            const response = await axios.post('/api/book/deleteIndex', {
                headers: {},
            });
            
            if (response.status === 200) {
                alert(response.data);
                fetchIndices();
            }
        } catch (error) {
            console.error(error);
        }
    };

    const navigate = useNavigate();
    const handleLogout = async () => {
        try {
          const storedToken = localStorage.getItem('jwtToken');
    
          if (!storedToken) {
            console.error('토큰이 없습니다.');
            return;
          }
          await axios.post('/api/admin/logout', storedToken);
    
          localStorage.removeItem('jwtToken');
         
        } catch (error) {
          console.error('로그아웃 오류:', error);
        } finally {
            navigate('/');
        }

      };

    const toggleFileContainer = () => {
        setShowFileContainer(true);
        setShowIndexListContainer(false);
      };
    
      const toggleIndexListContainer = () => {
        setShowIndexListContainer(true);
        setShowFileContainer(false);
      };
    
      useEffect(() => {
        
        const checkJwtValidity = async () => {
            const token = localStorage.getItem('jwtToken');
            try {
                const response = await fetch('/api/jwtCheck?jwt=' + token);
                const isValid = await response.text();
                if (isValid === 'true') {
                    // 유효하지 않은 토큰 처리
                } else {
                    navigate('/login');
                }
            } catch (error) {
                alert('네트워크 오류');
                navigate('/login');
            }
        };
        fetchIndices();
        checkJwtValidity();
    }, []);
    return (
        <div className='allPage'>
            {loading && (
                <div className="loading-overlay">
                <div className="loading-spinner"></div>
                </div>
            )}
            <div className="header">
                <div className='headerName'>도서 검색 사이트</div>
                <button className='logoutBtn' onClick={handleLogout}>로그아웃</button>
            </div>
            <div className="contentBox">
                <div className="left-menu">
                    <div className="admin_container">
                        <ul className="menu-ul">
                            <li className="menu-item" onClick={toggleFileContainer}>
                                <div className='menu-item-center'>
                                파일 업로드
                                </div>
                            </li>
                            <li className="menu-item" onClick={toggleIndexListContainer}>
                                <div className='menu-item-center'>
                                인덱스 확인
                                </div>
                            </li>
                        </ul>
                    </div>    
                </div>
                {showFileContainer && (
                <div className="fileContainer">
                    <div className='uploadBox'>
                        <div>
                            <input type="text" className="upload-name" 
                            value={uploadName} placeholder="엑셀파일을 업로드 하세요" disabled/>
                            <label htmlFor="upload-file" className='uploadBtn'>파일선택</label>
                            <input type="file" name="excelFile" onChange={handleFileChange} id="upload-file"/>
                        </div>
                        <br/>
                        <div>
                            <button onClick={autoIndex} className='autoIndexBtn'>자동 인덱싱</button>
                        </div>
                    </div>
                </div>
                )}
                {showIndexListContainer && (
                <div className="index-list-container">
                    <h2>인덱스 목록</h2>
                    <table className="table-container">
                        <thead>
                            <tr>
                                <th>상태</th>
                                <th>인덱스 명</th>
                                <th>UUID</th>
                                <th>기본 shard</th>
                                <th>복제 shard</th>
                                <th>문서 개수</th>
                                <th>삭제된 문서</th>
                                <th>저장소 크기</th>
                                <th>기본 저장소 크기</th>
                            </tr>
                        </thead>
                        <tbody>
                        {indices.map((index, indexKey) => (
                            <tr key={indexKey}>
                            <td>{index[0]}</td>
                            <td>{index[2]}</td>
                            <td>{index[3]}</td>
                            <td>{index[4]}</td>
                            <td>{index[5]}</td>
                            <td>{index[6]}</td>
                            <td>{index[7]}</td>
                            <td>{index[8]}</td>
                            <td>{index[9]}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                    <button onClick={deleteIndex} className="autoIndexBtn">인덱스 삭제</button>
                </div>
                )}
            </div>
        </div>
    );
};

export default Admin;
