# Build
custom_build(
    # Name of the container image
    'order-service',

    # Command to build the container image
    'gradlew bootBuildImage',

    tag = 'latest',

    # Files to watch that trigger a new build
    deps = ['build.gradle', 'src']
)

# Deploy
k8s_yaml(['k8s/deployment.yml', 'k8s/service.yml'])

# Manage
#k8s_resource('order-service', port_forwards=['9002'])
