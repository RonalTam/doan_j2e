<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thông báo</title>

    <!-- SweetAlert2 CDN -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <!-- Font đẹp (tuỳ chọn) -->
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@400;600&display=swap" rel="stylesheet">

    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Quicksand', sans-serif;
            background: linear-gradient(135deg, #74ebd5, #acb6e5); /* gradient nhẹ nhàng */
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
        }

        .fade-overlay {
            position: absolute;
            top: 0; left: 0;
            width: 100%;
            height: 100%;
            background: rgba(255,255,255,0.6);
            backdrop-filter: blur(4px);
            z-index: -1;
        }
    </style>
</head>
<body>
<div class="fade-overlay"></div>

<script>
    Swal.fire({
        icon: 'success',
        title: 'success!',
        text: '${message}', // thông báo từ servlet
        showConfirmButton: false,
        timer: 900,
        timerProgressBar: true,
        background: '#ffffff',
        color: '#333',
        customClass: {
            popup: 'swal2-rounded',
            title: 'swal2-title-custom',
            content: 'swal2-text-custom'
        },
        didClose: () => {
            window.location.href = '${redirect}';
        }
    });
</script>
</body>
</html>
