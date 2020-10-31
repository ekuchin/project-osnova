package ru.projectosnova.config;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigTypeRepository extends JpaRepository<ConfigType, String> {
}
