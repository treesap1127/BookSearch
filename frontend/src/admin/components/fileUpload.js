import React, { useState } from 'react';
import axios from 'axios';

const FileUpload = ({ onUploadSuccess }) => {
    const [selectedFile, setSelectedFile] = useState(null);

    const handleFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    const autoIndex = async () => {
        if (selectedFile) {
            try {
                const formData = new FormData();
                formData.append('excelFile', selectedFile);

                const response = await axios.post('/api/book/bookUpload', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                    },
                });
                
                if (response.status === 200) {
                    alert(response.data);
                    onUploadSuccess();
                }
            } catch (error) {
                console.error(error);
            }
        }
        else{
            alert('파일을 등록해주세요');
            return selectedFile.focus();
        }
    };

    const deleteIndex = async () => {
        try {
            const response = await axios.post('/api/book/deleteIndex', {
                headers: {},
            });
            
            if (response.status === 200) {
                alert(response.data);
            }
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div>
            <input type="file" name="excelFile" onChange={handleFileChange} />
            <button onClick={autoIndex}>autoIndex</button>
            <button onClick={deleteIndex}>deleteIndex</button>
        </div>
    );
};

export default FileUpload;
