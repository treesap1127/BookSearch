import React, { useState } from 'react';
import axios from 'axios';
import { Outlet, Link } from 'react-router-dom';
import FileUpload from './fileUpload';

const Indexing = () => {
    const [uploadSuccess, setUploadSuccess] = useState(false);

    const handleUploadSuccess = () => {
        setUploadSuccess(true);
    };

    return (
        <div>
            <FileUpload onUploadSuccess={handleUploadSuccess} />
            {uploadSuccess && <p>파일 업로드 성공!</p>}
            <div>
                <Link to="/">list</Link>
            </div>
            <Outlet />
        </div>
    );
}

export default Indexing;
