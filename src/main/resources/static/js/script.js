function openModal() {
  document.getElementById("modal").style.display = "flex";
}

function closeModal() {
  document.getElementById("modal").style.display = "none";
}

async function registerUser() {
  const user = {
    firstName: document.getElementById("firstName").value,
    surname: document.getElementById("surname").value,
    email: document.getElementById("email").value,
    mobile: document.getElementById("mobile").value
  };

  const res = await fetch("/api/register", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user)
  });

  const msg = await res.text();
  alert("Register: " + msg);
}

async function sendOtp() {
  const email = document.getElementById("email").value;

  const res = await fetch(`/api/send-otp?email=${encodeURIComponent(email)}`, {
    method: "GET"
  });

  const msg = await res.text();
  alert("Send OTP: " + msg);
  document.getElementById("otpSection").style.display = "block";
}

async function verifyOtp() {
  const email = document.getElementById("email").value;
  const otp = document.getElementById("otp").value;

  const res = await fetch(`/api/verify-otp?email=${email}&otp=${otp}`, {
    method: "POST"
  });

  if (res.ok) {
    const pdfUrl = await res.text();
    window.location.href = pdfUrl;
  } else {
    alert("OTP verification failed");
  }
}
