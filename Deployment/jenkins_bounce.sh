NODE_IP=$(./node_ip.sh)
ssh -i jenkins_server_keys.pem "ubuntu@${NODE_IP}" <<'EOF'
  PORT=$(lsof -t -i :8080)
  kill $PORT
  sudo docker run -d --network host -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts
EOF
